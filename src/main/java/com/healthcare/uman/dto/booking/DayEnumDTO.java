package com.healthcare.uman.dto.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DayEnumDTO {

    MONDAY("1"),
    TUESDAY("2"),
    WENESDAY("3"),
    THRUSDAY("4"),
    FRIDAY("5"),
    SATURDAY("6"),
    SUNDAY("7");

    private String value;

    DayEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DayEnumDTO fromValue(String value) {
        for (DayEnumDTO b : DayEnumDTO.values()) {
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
