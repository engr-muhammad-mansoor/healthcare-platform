package com.healthcare.uman.model.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SubscriptionEnum {

    MONTHLY("MONTHLY"),
    FREE("FREE"),

    ANNUALLY("ANNUALLY");

    private String value;

    SubscriptionEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SubscriptionEnum fromValue(String value) {
        for (SubscriptionEnum b : SubscriptionEnum.values()) {
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
