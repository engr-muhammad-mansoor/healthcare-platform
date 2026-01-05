package com.healthcare.uman.model.user;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.model.booking.Booking;
import com.healthcare.uman.model.medication.MedicalCard;
import com.healthcare.uman.model.professional.HumanPreference;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "user")
public class Patient extends User {

    private StatsPatient statsPatient;

    private MedicalCard medicalCard;

    private List<HumanPreference> preferences;

    private List<Professional> favoritesPro;

    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public StatsPatient getStatsPatient() {
        return statsPatient;
    }

    public void setStatsPatient(StatsPatient statsPatient) {
        this.statsPatient = statsPatient;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public List<HumanPreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<HumanPreference> preferences) {
        this.preferences = preferences;
    }

    public List<Professional> getFavoritesPro() {
        return favoritesPro;
    }

    public void setFavoritesPro(List<Professional> favoritesPro) {
        this.favoritesPro = favoritesPro;
    }
}
