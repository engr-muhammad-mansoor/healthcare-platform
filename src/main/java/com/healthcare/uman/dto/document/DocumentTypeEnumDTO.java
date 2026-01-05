package com.healthcare.uman.dto.document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentTypeEnumDTO {

    ADRESSAGE("ADRESSAGE"),
    MEDICATION("MEDICATION"),
    BIOLOGIE("BIOLOGIE"),
    CERTIFICAT("CERTIFICAT"),
    IMAGERIE("IMAGERIE");

    private String value;

    DocumentTypeEnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DocumentTypeEnumDTO fromValue(String value) {
        for (DocumentTypeEnumDTO b : DocumentTypeEnumDTO.values()) {
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
