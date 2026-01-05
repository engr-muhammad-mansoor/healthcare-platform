package com.healthcare.uman.model.professional;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.model.booking.DayEnum;
import com.healthcare.uman.model.booking.SlotTimeEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpeningHours implements Serializable {

    private DayEnum dayEnum;

    private SlotTimeEnum start;
    private SlotTimeEnum end;

    public DayEnum getDayEnum() {
        return dayEnum;
    }

    public void setDayEnum(DayEnum dayEnum) {
        this.dayEnum = dayEnum;
    }

    public SlotTimeEnum getStart() {
        return start;
    }

    public void setStart(SlotTimeEnum start) {
        this.start = start;
    }

    public SlotTimeEnum getEnd() {
        return end;
    }

    public void setEnd(SlotTimeEnum end) {
        this.end = end;
    }
}
