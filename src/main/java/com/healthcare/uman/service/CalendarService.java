package com.healthcare.uman.service;

// Import statements...

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.healthcare.uman.dto.booking.AvailabilityDTO;
import com.healthcare.uman.dto.booking.CalendarDTO;
import com.healthcare.uman.dto.booking.SlotDTO;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.CalendarMapper;
import com.healthcare.uman.model.booking.Availability;
import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.model.booking.Slot;
import com.healthcare.uman.model.booking.SlotStatusEnum;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.CalendarRepository;
import com.healthcare.uman.repository.UserRepository;

@Service
public class CalendarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarService.class);

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    public CalendarService(CalendarRepository calendarRepository, UserRepository userRepository) {
        this.calendarRepository = calendarRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new calendar based on provided CalendarDTO and associates it with a user.
     * The calendar is populated with available slots for the next 3 months, excluding holidays.
     *
     * @param calendarDTO Data transfer object containing calendar details.
     * @return The saved calendar entity.
     * @throws UserNotFoundException if the user specified in the DTO cannot be found.
     */
    public Calendar createCalendar(CalendarDTO calendarDTO) throws UserNotFoundException {
        LOGGER.debug("Starting to create a calendar with information: {}", calendarDTO);

        // Find the user associated with the calendar.
        Optional<User> userOptional = userRepository.findById(calendarDTO.getIdUser());
        if (!userOptional.isPresent()) {
            LOGGER.error("User not found with ID: {}", calendarDTO.getIdUser());
            throw new UserNotFoundException("User not found");
        }
        User user = userOptional.get();

        // Map the CalendarDTO to a Calendar entity.
        final Calendar calendar = CalendarMapper.INSTANCE.map(calendarDTO);
        calendar.setIdUser(user);

        // Define the time range for the calendar.
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(3);
        ZoneId zoneId = ZoneId.of("Europe/Paris");

        // Populate the calendar with available slots, excluding holidays.
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (!isHoliday(date)) {
                int dayNumber = date.getDayOfWeek().getValue();
                List<LocalTime> availableTimes = calendarDTO.getWeeklyAvailability().get(dayNumber);

                if (availableTimes != null && !availableTimes.isEmpty()) {
                    LocalDate finalDate = date;
                    List<Slot> slots = availableTimes.stream()
                                                     .map(time -> {
                                                         ZonedDateTime dateTime = ZonedDateTime.of(finalDate, time, zoneId); // Combine date and time
                                                         return new Slot(dateTime.toLocalTime(), null, SlotStatusEnum.AVAILABLE); // Use the combined datetime
                                                     })
                                                     .collect(Collectors.toList());
                    Availability availability = new Availability(date.atStartOfDay().toLocalDate(), slots);
                    calendar.addAvailability(availability);
                }
            }
        }

        // Save the populated calendar to the database.
        return calendarRepository.save(calendar);
    }

    // Planifie la tâche pour s'exécuter chaque dimanche soir
    @Scheduled(cron = "0 0 22 * * SUN")
    public void updateCalendars() {
        userRepository.findAll().forEach(user -> {
            Calendar calendar = calendarRepository.findByIdUser(user.getId());
            if (calendar != null) {
                updateCalendarWithWeeklyAvailability(calendar);
            }
        });
    }

    private void updateCalendarWithWeeklyAvailability(Calendar calendar) {
        LocalDate lastDate = getLastDateFromCalendar(calendar);
        LocalDate endDate = lastDate.plusWeeks(1);
        ZoneId zoneId = ZoneId.of("Europe/Paris");

        for (LocalDate date = lastDate.plusDays(1); !date.isAfter(endDate); date = date.plusDays(1)) {
            if (!isHoliday(date)) {
                int dayNumber = date.getDayOfWeek().getValue();
                List<LocalTime> availableTimes = calendar.getWeeklyAvailability().get(dayNumber);

                if (availableTimes != null) {
                    LocalDate finalDate = date;
                    List<Slot> slots = availableTimes.stream()
                                                     .map(time -> new Slot(ZonedDateTime.of(finalDate, time, zoneId).toLocalTime(), null, SlotStatusEnum.AVAILABLE))
                                                     .collect(Collectors.toList());
                    Availability availability = new Availability(date, slots);
                    calendar.addAvailability(availability);
                }
            }
        }
        calendarRepository.save(calendar);
    }

    private LocalDate getLastDateFromCalendar(Calendar calendar) {
        return calendar.getAvailabilities().stream()
                       .map(Availability::getDate)
                       .max(LocalDate::compareTo)
                       .orElse(LocalDate.now());
    }

    /**
     * Checks if a given date is a holiday.
     *
     * @param date The date to check.
     * @return True if the date is a holiday, false otherwise.
     */
    public boolean isHoliday(LocalDate date) {
        Set<LocalDate> holidays = Set.of(
                LocalDate.of(date.getYear(), 1, 1),  // Nouvel An
                LocalDate.of(date.getYear(), 5, 1),  // Fête du Travail
                LocalDate.of(date.getYear(), 7, 14), // Fête Nationale
                LocalDate.of(date.getYear(), 8, 15), // Assomption
                LocalDate.of(date.getYear(), 11, 1), // Toussaint
                LocalDate.of(date.getYear(), 11, 11),// Armistice
                LocalDate.of(date.getYear(), 12, 25) // Noël
        );
        return holidays.contains(date);
    }

    /**
     * Retrieves a calendar for a specific user, filtered for availability within the next specified number of months.
     *
     * @param id The user ID.
     * @param months The number of months to include in the calendar.
     * @return The filtered calendar.
     * @throws UserNotFoundException if no calendar exists for the specified user.
     */
    public Calendar getCalendarByUserId(String id, int months) throws UserNotFoundException {
        LOGGER.debug("Retrieving calendar for user ID: {}", id);

        final Calendar calendar = calendarRepository.findByIdUser(id);
        if (calendar != null) {
            LOGGER.debug("Calendar found for user ID: {}", id);

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusMonths(months);

            List<Availability> filteredAvailabilities = calendar.getAvailabilities().stream()
                                                                .filter(availability -> !availability.getDate().isBefore(startDate) && !availability.getDate().isAfter(endDate))
                                                                .collect(Collectors.toList());

            calendar.setAvailabilities(filteredAvailabilities);
            return calendar;
        }
        LOGGER.error("No calendar exists for user ID: {}", id);
        throw new UserNotFoundException("No calendar exists for this user");
    }

    /**
     * Updates an existing calendar with new availability details provided in the CalendarDTO.
     *
     * @param calendarDTO The DTO containing the updated calendar details.
     * @param id The ID of the calendar to update.
     * @return The updated calendar entity.
     */
    public Calendar updateCalendar(CalendarDTO calendarDTO, String id) {
        LOGGER.debug("Updating calendar with ID: {}", id);
        Optional<Calendar> calendarOptional = calendarRepository.findById(id);

        if (calendarOptional.isPresent()) {
            Calendar existingCalendar = calendarOptional.get();

            // Update calendar name if provided.
            if (calendarDTO.getName() != null) {
                existingCalendar.setName(calendarDTO.getName());
            }

            // Process each provided availability update.
            for (AvailabilityDTO availabilityDTO : calendarDTO.getAvailabilities()) {
                Optional<Availability> matchingAvailability = existingCalendar.getAvailabilities()
                                                                              .stream()
                                                                              .filter(availability -> availability.getDate().equals(availabilityDTO.getDate()))
                                                                              .findFirst();

                if (matchingAvailability.isPresent()) {
                    Availability availability = matchingAvailability.get();

                    List<Slot> updatedSlots = availability.getSlots()
                                                          .stream()
                                                          .map(slot -> {
                                                              Optional<SlotDTO> matchingSlotDTO = availabilityDTO.getSlots()
                                                                                                                 .stream()
                                                                                                                 .filter(slotDTO -> slotDTO.getTime().equals(slot.getTime()))
                                                                                                                 .findFirst();

                                                              if (matchingSlotDTO.isPresent()) {
                                                                  slot.setTime(matchingSlotDTO.get().getNewTime());
                                                                  slot.setSlotStatusEnum(SlotStatusEnum.AVAILABLE);
                                                              } else {
                                                                  slot.setSlotStatusEnum(SlotStatusEnum.BOOKED);
                                                              }
                                                              return slot;
                                                          })
                                                          .collect(Collectors.toList());

                    availability.setSlots(updatedSlots);
                } else {
                    // Création de nouveaux créneaux pour une nouvelle date
                    List<Slot> newSlots = availabilityDTO.getSlots().stream()
                                                         .map(slotDTO -> new Slot(slotDTO.getTime(), slotDTO.getNewTime(), SlotStatusEnum.AVAILABLE))
                                                         .collect(Collectors.toList());

                    Availability newAvailability = new Availability(availabilityDTO.getDate(), newSlots);
                    existingCalendar.addAvailability(newAvailability);
                }
            }

            // Save and return the updated calendar.
            return calendarRepository.save(existingCalendar);
        }
        LOGGER.error("Failed to find calendar with ID: {}", id);
        return null;
    }

    /**
     * Disables (soft delete) a calendar by setting its active flag to false.
     *
     * @param id The ID of the calendar to disable.
     */
    public void disableCalendar(String id) {
        LOGGER.debug("Disabling calendar with ID: {}", id);
        final Optional<Calendar> calendarToDelete = calendarRepository.findById(id);
        if (calendarToDelete.isPresent()) {
            Calendar calendar = calendarToDelete.get();
            calendar.setActive(false);
            calendarRepository.save(calendar);
        } else {
            LOGGER.error("Failed to find calendar to disable with ID: {}", id);
        }
    }

}
