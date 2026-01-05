package com.healthcare.uman.model.connexion;

import java.time.Instant;
import java.util.List;

import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.model.professional.ProfessionalCard;
import com.healthcare.uman.model.user.Address;
import com.healthcare.uman.model.user.GenderEnum;

import jakarta.validation.constraints.NotBlank;

public class UserRegistrationRequest {

    @NotBlank(message = "The password is required.")
    private String password;
    @NotBlank(message = "The firstName is required.")
    private String firstName;

    private GenderEnum gender;
    @NotBlank(message = "The lastName is required.")
    private String lastName;

    private String lastNameMarried;

    @NotBlank(message = "The email is required.")
    private String email;

    private String profile;

    private String datePassword;

    private Instant birthDate;

    private String birthCity;

    private String type;

    private Instant creationDate;

    private Instant updateDate;

    private Address address;

    private Double rating;

    private List<Calendar> calendars;

    private ProfessionalCard professionalCard;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameMarried() {
        return lastNameMarried;
    }

    public void setLastNameMarried(String lastNameMarried) {
        this.lastNameMarried = lastNameMarried;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDatePassword() {
        return datePassword;
    }

    public void setDatePassword(String datePassword) {
        this.datePassword = datePassword;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
