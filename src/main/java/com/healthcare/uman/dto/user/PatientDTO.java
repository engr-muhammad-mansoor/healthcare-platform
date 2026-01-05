package com.healthcare.uman.dto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.dto.booking.BookingDTO;
import com.healthcare.uman.dto.medication.MedicalCardDTO;
import com.healthcare.uman.dto.professional.HumanPreferencesDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO extends UserDTO {

    private StatsPatientDTO statsPatient;
    private List<FamilyDTO> familyDTOS;

    private MedicalCardDTO medicalCard;

    private List<HumanPreferencesDTO> preferences;

    private List<ProfessionalDTO> favoritesPro;

    private List<BookingDTO> bookings;

    public List<FamilyDTO> getFamilyDTOS() {
        return familyDTOS;
    }

    public void setFamilyDTOS(List<FamilyDTO> familyDTOS) {
        this.familyDTOS = familyDTOS;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    public StatsPatientDTO getStatsPatient() {
        return statsPatient;
    }

    public void setStatsPatient(StatsPatientDTO statsPatient) {
        this.statsPatient = statsPatient;
    }

    public MedicalCardDTO getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCardDTO medicalCard) {
        this.medicalCard = medicalCard;
    }

    public List<HumanPreferencesDTO> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<HumanPreferencesDTO> preferences) {
        this.preferences = preferences;
    }

    public List<ProfessionalDTO> getFavoritesPro() {
        return favoritesPro;
    }

    public void setFavoritesPro(List<ProfessionalDTO> favoritesPro) {
        this.favoritesPro = favoritesPro;
    }
}
