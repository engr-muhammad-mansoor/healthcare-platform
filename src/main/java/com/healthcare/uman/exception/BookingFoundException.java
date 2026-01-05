package com.healthcare.uman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.healthcare.uman.model.booking.Booking;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "booking found")
public class BookingFoundException extends Exception {
    private Booking existingBooking;

    public BookingFoundException(Booking existingBooking) {
        super("Booking found");
        this.existingBooking = existingBooking;
    }

    public BookingFoundException() {
        super("No booking found");
    }

    public Booking getExistingBooking() {
        return existingBooking;
    }
}
