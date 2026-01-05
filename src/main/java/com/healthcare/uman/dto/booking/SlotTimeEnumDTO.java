package com.healthcare.uman.dto.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SlotTimeEnumDTO {

    MIDNIGHT("0", "0"),
    MIDNIGHT_HALF("0", "30"),
    ONE("1", "0"),
    ONE_HALF("1", "30"),
    TWO("2", "0"),
    TWO_HALF("2", "30"),
    THREE("3", "0"),
    THREE_HALF("3", "30"),
    FOUR("4", "0"),
    FOUR_HALF("4", "30"),
    FIVE("5", "0"),
    FIVE_HALF("5", "30"),
    SIX("6", "0"),
    SIX_HALF("6", "30"),
    SEVEN("7", "0"),
    SEVEN_HALF("7", "30"),
    EIGHT("8", "0"),
    EIGHT_HALF("8", "30"),
    NINE("9", "0"),
    NINE_HALF("9", "30"),
    TEN("10", "0"),
    TEN_HALF("10", "30"),
    ELEVEN("11", "0"),
    ELEVEN_HALF("11", "30"),
    TWELVE("12", "0"),
    TWELVE_HALF("12", "30"),
    THIRTEEN("13", "0"),
    THIRTEEN_HALF("13", "30"),
    FOURTEEN("14", "0"),
    FOURTEEN_HALF("14", "30"),
    FIFTEEN("15", "0"),
    FIFTEEN_HALF("15", "30"),
    SIXTEEN("16", "0"),
    SIXTEEN_HALF("16", "30"),
    SEVENTEEN("17", "0"),
    SEVENTEEN_HALF("17", "30"),
    EIGHTEEN("18", "0"),
    EIGHTEEN_HALF("18", "30"),
    NINETEEN("19", "0"),
    NINETEEN_HALF("19", "30"),
    TWENTY("20", "0"),
    TWENTY_HALF("20", "30"),
    TWENTY_ONE("21", "0"),
    TWENTY_ONE_HALF("21", "30"),
    TWENTY_TWO("22", "0"),
    TWENTY_TWO_HALF("22", "30"),
    TWENTY_THREE("23", "0"),
    TWENTY_THREE_HALF("23", "30");

    private String hour;
    private String minute;

    SlotTimeEnumDTO(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @JsonCreator
    public static SlotTimeEnumDTO fromValue(String value) {
        for (SlotTimeEnumDTO b : SlotTimeEnumDTO.values()) {
            if (b.getValue().equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    @JsonValue
    public String getValue() {
        return hour + ":" + minute;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }
}
