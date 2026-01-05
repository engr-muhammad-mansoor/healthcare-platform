package com.healthcare.uman.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserEnum {

    PATIENT("PATIENT"),

    PRO("PRO");

    private String value;

    UserEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UserEnum fromValue(String value) {
        for (UserEnum b : UserEnum.values()) {
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
