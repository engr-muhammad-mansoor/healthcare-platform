package com.healthcare.uman.model.booking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SlotTimeEnum {

    MIDNIGHT("0:00"), MIDNIGHT_HALF("0:30"), ONE("1:00"), ONE_HALF("1:30"), TWO("2:00"), TWO_HALF("2:30"), THREE("3:00"), THREE_HALF("3:30"), FOUR("4:00"), FOUR_HALF("4:30"), FIVE("5:00"), FIVE_HALF("5:30"), SIX("6:00"), SIX_HALF("6:30"), SEVEN("7:00"), SEVEN_HALF("7:30"), EIGHT("8:00"), EIGHT_HALF("8:30"), NINE("9:00"), NINE_HALF("9:30"), TEN("10:00"), TEN_HALF("10:30"), ELEVEN("11:00"), ELEVEN_HALF("11:30"), TWELVE("12:00"), TWELVE_HALF("12:30"), THIRTEEN("13:00"), THIRTEEN_HALF("13:30"), FOURTEEN("14:00"), FOURTEEN_HALF("14:30"), FIFTEEN("15:00"), FIFTEEN_HALF("15:30"), SIXTEEN("16:00"), SIXTEEN_HALF("16:30"), SEVENTEEN("17:00"), SEVENTEEN_HALF("17:30"), EIGHTEEN("18:00"), EIGHTEEN_HALF("18:30"), NINETEEN("19:00"), NINETEEN_HALF("19:30"), TWENTY("20:00"), TWENTY_HALF("20:30"), TWENTY_ONE("21:00"), TWENTY_ONE_HALF("21:30"), TWENTY_TWO("22:00"), TWENTY_TWO_HALF("22:30"), TWENTY_THREE("23:00"), TWENTY_THREE_HALF("23:30");

    private final String value;

    SlotTimeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SlotTimeEnum fromValue(String value) {
        for (SlotTimeEnum b : SlotTimeEnum.values()) {
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
