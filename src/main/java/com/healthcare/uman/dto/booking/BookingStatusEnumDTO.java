package com.healthcare.uman.dto.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatusEnumDTO {

    COMING("COMING"),
    CANCELED_BY_USER("CANCELED_BY_USER"),
    CANCELED_BY_PRO("CANCELED_BY_PRO"),
    DONE("DONE");

    private String value;

    BookingStatusEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BookingStatusEnumDTO fromValue(String value) {
        for (BookingStatusEnumDTO b : BookingStatusEnumDTO.values()) {
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
