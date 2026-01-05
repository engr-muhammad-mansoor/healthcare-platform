package com.healthcare.uman.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProviderEnum {

    LOCAL("LOCAL"),
    APPLE("APPLE"),
    FACEBOOK("FACEBOOK"),

    GOOGLE("GOOGLE");

    private String value;

    ProviderEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ProviderEnum fromValue(String value) {
        for (ProviderEnum b : ProviderEnum.values()) {
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
