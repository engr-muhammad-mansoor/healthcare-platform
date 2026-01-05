package com.healthcare.uman.model.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingEnum {

    VISIO("VISIO"),

    OFFICE("OFFICE");

    private final String value;

    BookingEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BookingEnum fromValue(String value) {
        for (BookingEnum b : BookingEnum.values()) {
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
