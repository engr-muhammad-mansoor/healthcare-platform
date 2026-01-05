package com.healthcare.uman.model.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SlotStatusEnum {

    BOOKED("BOOKED"), DISABLED("DISABLED"), AVAILABLE("AVAILABLE");

    private final String value;

    SlotStatusEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SlotStatusEnum fromValue(String value) {
        for (SlotStatusEnum b : SlotStatusEnum.values()) {
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
