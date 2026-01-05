package com.healthcare.uman.model.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentModeEnum {

    CARD("CARD"),
    CASH("CASH"),

    CHECK("CHECK");

    private String value;

    PaymentModeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PaymentModeEnum fromValue(String value) {
        for (PaymentModeEnum b : PaymentModeEnum.values()) {
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
