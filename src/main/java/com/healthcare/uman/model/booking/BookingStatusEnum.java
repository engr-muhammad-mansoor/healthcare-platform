package com.healthcare.uman.model.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatusEnum {

    COMING("COMING"), CANCELED_BY_USER("CANCELED_BY_USER"), CANCELED_BY_PRO("CANCELED_BY_PRO"), DONE("DONE");

    private final String value;

    BookingStatusEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BookingStatusEnum fromValue(String value) {
        for (BookingStatusEnum b : BookingStatusEnum.values()) {
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
