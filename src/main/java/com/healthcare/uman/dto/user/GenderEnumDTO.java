package com.healthcare.uman.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnumDTO {

    MALE("MALE"),

    FEMALE("FEMALE");

    private String value;

    GenderEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static GenderEnumDTO fromValue(String value) {
        for (GenderEnumDTO b : GenderEnumDTO.values()) {
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
