package com.healthcare.uman.dto.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingEnumDTO {

    VISIO("VISIO"),

    OFFICE("OFFICE");

    private String value;

    BookingEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BookingEnumDTO fromValue(String value) {
        for (BookingEnumDTO b : BookingEnumDTO.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
