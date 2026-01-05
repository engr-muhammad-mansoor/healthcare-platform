package com.healthcare.uman.dto.booking;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarDTO implements Serializable {

    private String id;

    private List<BookingDTO> bookings;

    private String name;

    private List<AvailabilityDTO> availabilities;

    private Map<Integer, List<LocalTime>> weeklyAvailability;

    private String idUser;

    /**
     * A calendar can be active or inactive
     */
    private Boolean active;

    public Map<Integer, List<LocalTime>> getWeeklyAvailability() {
        return weeklyAvailability;
    }

    public void setWeeklyAvailability(Map<Integer, List<LocalTime>> weeklyAvailability) {
        this.weeklyAvailability = weeklyAvailability;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AvailabilityDTO> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityDTO> availabilities) {
        this.availabilities = availabilities;
    }

}
