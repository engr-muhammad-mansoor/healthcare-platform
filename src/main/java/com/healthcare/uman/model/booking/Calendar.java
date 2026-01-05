package com.healthcare.uman.model.booking;

import com.healthcare.uman.model.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document(collection = "calendar")
@Getter
@Setter
public class Calendar implements Serializable {

    @Id
    private final String id = UUID.randomUUID().toString();

    /**
     * A calendar can be active or inactive
     */
    private boolean active = true;

    @DBRef
    private List<Booking> bookings;

    private String name;

    private List<Availability> availabilities;

    private boolean bookingDisabled;

    private Map<Integer, List<LocalTime>> weeklyAvailability;

    @DBRef
    private User idUser;

    public Map<Integer, List<LocalTime>> getWeeklyAvailability() {
        return weeklyAvailability;
    }

    public void setWeeklyAvailability(Map<Integer, List<LocalTime>> weeklyAvailability) {
        this.weeklyAvailability = weeklyAvailability;
    }

    public void addAvailability(Availability availability) {
        if (this.availabilities == null) {
            this.availabilities = new ArrayList<>();
        }
        if (availability != null) {
            this.availabilities.add(availability);
        }
    }

    public void addBooking(Booking booking) {
        if (this.bookings == null) {
            this.bookings = new ArrayList<>();
        }
        if (booking != null) {
            this.bookings.add(booking);
        }
    }

    public void setActive(boolean b) {
        this .active = b;
    }
}
