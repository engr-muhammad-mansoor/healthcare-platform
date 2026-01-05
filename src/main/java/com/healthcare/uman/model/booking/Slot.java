package com.healthcare.uman.model.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "slot")
public class Slot implements Serializable {

    private LocalTime time;

    private LocalTime newTime;

    private SlotStatusEnum slotStatusEnum;

    public Slot(LocalTime time, LocalTime newTime) {
        this.time = time;
        this.newTime = newTime;
    }

    public void setTime(LocalTime newTime) {
        this.time = newTime;
    }
}
