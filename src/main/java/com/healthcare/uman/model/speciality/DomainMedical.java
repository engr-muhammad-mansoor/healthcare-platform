package com.healthcare.uman.model.speciality;

public enum DomainMedical {
    KINE("KINE"),
    GENERALISTE("GENERALISTE"),
    DENTISTE("DENTISTE"),
    SAGEFEMME("SAGEFEMME"),
    OSTEO("OSTEO"),
    OPTHALMO("OPTHALMO");

    private final String name;

    DomainMedical(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
