package com.healthcare.uman.dto.professional;

public enum LanguageDTO {
    ENGLISH("English"),
    SPANISH("Spanish"),
    CHINESE("Chinese"),
    HINDI("Hindi"),
    ARABIC("Arabic"),
    FRENCH("French"),
    RUSSIAN("Russian"),
    PORTUGUESE("Portuguese"),
    BENGALI("Bengali"),
    URDU("Urdu"),
    GERMAN("German"),
    JAPANESE("Japanese"),
    SWAHILI("Swahili"),
    KOREAN("Korean"),
    TURKISH("Turkish"),
    ITALIAN("Italian"),
    DUTCH("Dutch"),
    POLISH("Polish"),
    VIETNAMESE("Vietnamese"),
    TAMIL("Tamil");

    private final String name;

    LanguageDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
