package com.healthcare.uman.model.speciality;

public enum HumanPreferenceEnum {
    SOCIABLE("Sociable"),
    ATTENTIONNE("Attentionné"),
    LOGIQUE("Logique"),
    ENERGIQUE("Énergique"),
    SCIENTIFIQUE("Scientifique"),
    ENTHOUSIASTE("Enthousiaste"),
    CALME("Calme"),
    RIGOUREUX("Rigoureux"),
    EMPATHIQUE("Empathique"),
    COMMUNICATIF("Communicatif");

    private final String value;

    HumanPreferenceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HumanPreferenceEnum fromValue(String value) {
        for (HumanPreferenceEnum preference : values()) {
            if (preference.getValue().equalsIgnoreCase(value)) {
                return preference;
            }
        }
        throw new IllegalArgumentException("Valeur de préférence humaine non valide : " + value);
    }
}
