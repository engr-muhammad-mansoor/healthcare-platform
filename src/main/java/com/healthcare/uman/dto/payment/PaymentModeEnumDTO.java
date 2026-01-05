package com.healthcare.uman.dto.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum PaymentModeEnumDTO {

    CARD("CARD"),
    CASH("CASH"),

    CHECK("CHECK");

    private String value;

    PaymentModeEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PaymentModeEnumDTO fromValue(String value) {
        for (PaymentModeEnumDTO b : PaymentModeEnumDTO.values()) {
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
