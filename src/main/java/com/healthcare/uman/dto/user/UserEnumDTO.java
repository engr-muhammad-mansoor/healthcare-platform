package com.healthcare.uman.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserEnumDTO {

    PATIENT("PATIENT"),

    PRO("PRO");

    private String value;

    UserEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UserEnumDTO fromValue(String value) {
        for (UserEnumDTO b : UserEnumDTO.values()) {
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
