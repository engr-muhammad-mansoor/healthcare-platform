package com.healthcare.uman.dto.booking;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.model.booking.SlotStatusEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlotDTO implements Serializable {
    private LocalTime time;
    private LocalTime newTime;

    private SlotStatusEnum slotStatusEnum;

    public SlotDTO(LocalTime time, LocalTime newTime) {
        this.time = time;
        this.newTime = newTime;
    }

    public SlotDTO() {
    }

    public SlotDTO(LocalTime time) {
        this.time = time;
    }
    public SlotDTO(String timeStr) {
        this.time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getNewTime() {
        return newTime;
    }

    public void setNewTime(LocalTime newTime) {
        this.newTime = newTime;
    }

    public SlotStatusEnum getSlotStatusEnum() {
        return slotStatusEnum;
    }

    public void setSlotStatusEnum(SlotStatusEnum slotStatusEnum) {
        this.slotStatusEnum = slotStatusEnum;
    }
}
