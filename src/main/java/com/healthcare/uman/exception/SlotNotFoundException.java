package com.healthcare.uman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Slot not found")
public class SlotNotFoundException extends Exception {
    public SlotNotFoundException() {
    }

    public SlotNotFoundException(String message) {
        super(message);
    }
}
