package com.healthcare.uman.dto.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum SubscriptionEnumDTO {

    MONTHLY("MONTHLY"),
    FREE("FREE"),

    ANNUALLY("ANNUALLY");

    private String value;

    SubscriptionEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SubscriptionEnumDTO fromValue(String value) {
        for (SubscriptionEnumDTO b : SubscriptionEnumDTO.values()) {
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
