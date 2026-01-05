package com.healthcare.uman.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.booking.BookingDTO;
import com.healthcare.uman.exception.BookingFoundException;
import com.healthcare.uman.exception.SlotNotFoundException;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.BookingMapper;
import com.healthcare.uman.model.booking.Booking;
import com.healthcare.uman.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Booking API")
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @SupervisorLogAudit
    @PostMapping
    @Operation(summary = "Create a new booking", description = "Returns the new booking")
    public ResponseEntity<BookingDTO> addBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        LOGGER.debug("Call addBooking with informations : {}", bookingDTO.toString());
        Booking bookingSaved;
        try {
            bookingSaved = bookingService.addBooking(bookingDTO);
        } catch (UserNotFoundException | SlotNotFoundException e) {
            LOGGER.error("Failed to add booking: User not found. Reason: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BookingFoundException e) {
            LOGGER.error("Failed to add booking: Booking found. Reason: {}", e.getMessage());
            Booking existingBooking = e.getExistingBooking();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(BookingMapper.INSTANCE.map(existingBooking));
        }
        LOGGER.info("Booking added successfully.");
        return ResponseEntity.ok().body(BookingMapper.INSTANCE.map(bookingSaved));
    }

    @SupervisorLogAudit
    @PutMapping("/{id}")
    @Operation(summary = "Update a booking's information", description = "Update a booking's information")
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO, @PathVariable String id) throws SlotNotFoundException {
        final Booking booking;
        try {
            booking = bookingService.updateBooking(bookingDTO, id);
        } catch (BookingFoundException e) {
            LOGGER.error("Failed to update booking: Booking found. Reason: {}", e.getMessage());
            Booking existingBooking = e.getExistingBooking();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(BookingMapper.INSTANCE.map(existingBooking));
        }
        LOGGER.debug("Booking updated with id: {}", id);
        return ResponseEntity.ok().body(BookingMapper.INSTANCE.map(booking));
    }

    @SupervisorLogAudit
    @GetMapping("/{id}")
    @Operation(summary = "Get all the bookings for an user by user Id and UserType (Pro or patient", description = "Get all the bookings for user by user Id")
    public ResponseEntity<List<BookingDTO>> getBookingForUser(@PathVariable String id, @PathVariable String userType) {
        try {
            return ResponseEntity.ok().body(BookingMapper.INSTANCE.mapBooking(bookingService.getBookingsForPatient(id)));
        } catch (Exception e) {
            LOGGER.error("Failed to get bookings for patient: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
