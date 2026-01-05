package com.healthcare.uman.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.uman.dto.booking.BookingDTO;
import com.healthcare.uman.exception.BookingFoundException;
import com.healthcare.uman.exception.SlotNotFoundException;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.BookingMapper;
import com.healthcare.uman.model.booking.Booking;
import com.healthcare.uman.model.booking.BookingStatusEnum;
import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.model.booking.Slot;
import com.healthcare.uman.model.booking.SlotStatusEnum;
import com.healthcare.uman.model.user.Patient;
import com.healthcare.uman.model.user.Professional;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.BookingRepository;
import com.healthcare.uman.repository.CalendarRepository;
import com.healthcare.uman.repository.PatientRepository;
import com.healthcare.uman.repository.ProfessionalRepository;

@Service
public class BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);
    private final BookingRepository bookingRepository;
    private final CalendarRepository calendarRepository;
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CalendarRepository calendarRepository, PatientRepository patientRepository, ProfessionalRepository professionalRepository) {
        this.bookingRepository = bookingRepository;
        this.calendarRepository = calendarRepository;
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
    }

    public Booking addBooking(BookingDTO bookingDTO) throws UserNotFoundException, BookingFoundException, SlotNotFoundException {
        LOGGER.debug("Starting to add a booking with information: {}", bookingDTO);

        final Booking booking = BookingMapper.INSTANCE.map(bookingDTO);

        // Attempt to find the professional and patient involved in the booking.
        final Optional<Professional> professionalOptional = professionalRepository.findById(booking.getProfessionalId().getId());
        final Optional<Patient> patientOptional = patientRepository.findById(booking.getPatientId().getId());

        // Ensure both the professional and patient exist before proceeding.
        if (!professionalOptional.isPresent() || !patientOptional.isPresent()) {
            LOGGER.error("Professional or patient not found for booking: {}", bookingDTO);
            throw new UserNotFoundException("Professional or patient does not exist.");
        }

        checkBookingConflicts(booking);

        booking.setCreatedDate(new Date());
        booking.setActive(Boolean.TRUE);

        final Booking bookingSaved = bookingRepository.save(booking);
        updateSlot(professionalOptional.get().getId(), bookingSaved, SlotStatusEnum.BOOKED);
        return bookingSaved;
    }

    private void updateSlot(String idUser, Booking booking, SlotStatusEnum slotStatusEnum) throws SlotNotFoundException {
        Calendar calendar = calendarRepository.findByIdUser(idUser);

        Optional<Slot> matchingSlotOptional = calendar.getAvailabilities().stream()
                                                      .filter(availability -> availability.getDate().equals(booking.getDate()))
                                                      .flatMap(availability -> availability.getSlots().stream())
                                                      .filter(slot -> slot.getTime().equals(booking.getSlots().getTime()) && slot.getSlotStatusEnum().equals(
                                                              SlotStatusEnum.AVAILABLE))
                                                      .findFirst();

        if (matchingSlotOptional.isPresent()) {
            Slot matchingSlot = matchingSlotOptional.get();
            matchingSlot.setSlotStatusEnum(slotStatusEnum);
            calendar.addBooking(booking);
            calendarRepository.save(calendar);
        } else {
            throw new SlotNotFoundException();
        }
    }

    /**
     * Release the slot after a cancellation
     * @param calendarId
     * @param booking
     * @throws SlotNotFoundException
     */
    private void releaseSlot(String calendarId, Booking booking) throws SlotNotFoundException {
        Optional<Calendar> calendar = Optional.ofNullable(calendarRepository.findByIdUser(calendarId));
        if (calendar.isPresent()) {
            LocalDate bookingDate = booking.getDate();
            Calendar cal = calendar.get();

            Optional<Slot> matchingSlotOptional = cal.getAvailabilities().stream()
                                                          .filter(availability -> availability.getDate().equals(bookingDate))
                                                          .flatMap(availability -> availability.getSlots().stream())
                                                          .filter(slot -> slot.getTime().equals(booking.getSlots().getTime()))
                                                          .findFirst();

            if (matchingSlotOptional.isPresent()) {
                Slot matchingSlot = matchingSlotOptional.get();
                matchingSlot.setSlotStatusEnum(SlotStatusEnum.AVAILABLE);
                calendarRepository.save(cal);
            } else {
                throw new SlotNotFoundException();
            }
        }
    }

    private void checkBookingConflicts(Booking booking) throws BookingFoundException {
        if (booking == null || booking.getPatientId() == null || booking.getProfessionalId() == null) {
            LOGGER.error("Booking, patient ID, or professional ID is null.");
            throw new IllegalArgumentException("Booking, patient ID, or professional ID cannot be null.");
        }

        LOGGER.debug("Checking for booking conflicts for patient ID: {} and professional ID: {}",
                booking.getPatientId().getId(), booking.getProfessionalId().getId());

        List<Booking> existingBookings = bookingRepository.findByPatientIdAndProfessionalIdAndActiveIsTrue(
                booking.getPatientId(), booking.getProfessionalId(), true);

        if (existingBookings == null || existingBookings.isEmpty()) {
            LOGGER.debug("No existing bookings found. No conflict possible.");
            return;
        }

        LocalTime newBookingTime = booking.getSlots().getTime();

        for (Booking existingBooking : existingBookings) {
            if (existingBooking.getActive().booleanValue()) {
                LocalTime existingBookingTime = existingBooking.getSlots().getTime();

                if (existingBookingTime == null) {
                    LOGGER.warn("Found an existing booking with null start time. Skipping this booking.");
                    continue;
                }

                long minutesBetween = Math.abs(ChronoUnit.MINUTES.between(newBookingTime, existingBookingTime));
                if (minutesBetween < 15) {
                    LOGGER.error("Detected a booking conflict with an existing booking ID: {} due to insufficient time gap.", existingBooking.getId());
                    throw new BookingFoundException(existingBooking);
                }
            }
        }

        LOGGER.debug("No booking conflicts found for patient ID: {} and professional ID: {}",
                booking.getPatientId().getId(), booking.getProfessionalId().getId());
    }

    /**
     * Update a booking
     *
     * @param bookingDTO
     * @param id
     *
     * @return
     */
    public Booking updateBooking(BookingDTO bookingDTO, String id) throws BookingFoundException, SlotNotFoundException {
        final Booking updatedBooking = BookingMapper.INSTANCE.map(bookingDTO);
        LOGGER.debug("updateBooking for the booking id : {}", id);

        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking existingBooking = bookingOptional.get();

            if (updatedBooking != null && (updatedBooking.getStatus().equals(BookingStatusEnum.CANCELED_BY_USER)
                    || updatedBooking.getStatus().equals(BookingStatusEnum.CANCELED_BY_PRO))) {
                existingBooking.setActive(Boolean.FALSE);
                existingBooking.setStatus(updatedBooking.getStatus());

                // Release the slot
                releaseSlot(existingBooking.getProfessionalId().getId(), existingBooking);
            } else {

                if (updatedBooking.getDate() != null) {
                    existingBooking.setDate(updatedBooking.getDate());
                }
                if (updatedBooking.getSlots() != null) {
                    existingBooking.setSlots(updatedBooking.getSlots());
                }
                if (updatedBooking.getDocuments() != null) {
                    existingBooking.setDocuments(updatedBooking.getDocuments());
                }
                existingBooking.setAlertEarlier(updatedBooking.isAlertEarlier());
                existingBooking.setModifiedDate(new Date());
            }
            return bookingRepository.save(existingBooking);
        }
        return null;
    }

    /**
     * Get all bookings by patient's id
     *
     * @param id
     */
    public List<Booking> getBookingsForPatient(String id) throws UserNotFoundException {
        LOGGER.debug("getBookingsForPatient for booking Id : {}", id);
        User user = new User();
        user.setId(id);

        final List<Booking> bookings = bookingRepository.findByPatientId(user);
        if (bookings != null) {
            LOGGER.debug("getBookingsForPatient for user : {} - bookings existing", id);
            return bookings;
        }
        throw new UserNotFoundException("Aucun booking n'existe");
    }

    /**
     * Get all bookings by pro's id
     *
     * @param id
     */
    public List<Booking> getBookingsForPro(String id) throws UserNotFoundException {
        LOGGER.debug("getBookingsForPro for booking Id : {}", id);
        User user = new User();
        user.setId(id);

        final List<Booking> bookings = bookingRepository.findByProfessionalId(user);
        if (bookings != null) {
            LOGGER.debug("getBookingsForPro for user : {} - bookings existing", id);
            return bookings;
        }
        throw new UserNotFoundException("Aucun booking n'existe");
    }

    /**
     * Disable a booking by ID
     *
     * @param id
     */
    public void disableBooking(String id) {
        LOGGER.debug("Disable the booking id : {}", id);
        final Optional<Booking> bookingDeleted = bookingRepository.findById(id);
        if (bookingDeleted.isPresent()) {
            Booking booking = bookingDeleted.get();
            booking.setActive(false);
            booking.setStatus(BookingStatusEnum.CANCELED_BY_USER);
            bookingRepository.save(booking);
        }
    }
}

