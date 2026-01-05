package com.healthcare.uman.dto.professional;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.dto.booking.DayEnumDTO;
import com.healthcare.uman.dto.booking.SlotTimeEnumDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpeningHoursDTO implements Serializable {

    private DayEnumDTO dayEnum;

    private SlotTimeEnumDTO start;
    private SlotTimeEnumDTO end;

    public DayEnumDTO getDayEnum() {
        return dayEnum;
    }

    public void setDayEnum(DayEnumDTO dayEnum) {
        this.dayEnum = dayEnum;
    }

    public SlotTimeEnumDTO getStart() {
        return start;
    }

    public void setStart(SlotTimeEnumDTO start) {
        this.start = start;
    }

    public SlotTimeEnumDTO getEnd() {
        return end;
    }

    public void setEnd(SlotTimeEnumDTO end) {
        this.end = end;
    }
}
