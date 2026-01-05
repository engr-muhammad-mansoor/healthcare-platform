package com.healthcare.uman.model.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DayEnum {

    MONDAY("1"), TUESDAY("2"), WENESDAY("3"), THRUSDAY("4"), FRIDAY("5"), SATURDAY("6"), SUNDAY("7");

    private final String value;

    DayEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DayEnum fromValue(String value) {
        for (DayEnum b : DayEnum.values()) {
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
