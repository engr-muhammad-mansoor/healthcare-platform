package com.healthcare.uman.model.document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentTypeEnum {

    ADRESSAGE("ADRESSAGE"),
    MEDICATION("MEDICATION"),
    BIOLOGIE("BIOLOGIE"),
    CERTIFICAT("CERTIFICAT"),
    IMAGERIE("IMAGERIE");

    private String value;

    DocumentTypeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DocumentTypeEnum fromValue(String value) {
        for (DocumentTypeEnum b : DocumentTypeEnum.values()) {
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
