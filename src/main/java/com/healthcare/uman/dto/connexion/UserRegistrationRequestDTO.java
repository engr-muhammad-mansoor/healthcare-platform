package com.healthcare.uman.dto.connexion;

import java.time.Instant;

import com.healthcare.uman.dto.user.AddressDTO;
import com.healthcare.uman.dto.user.GenderEnumDTO;

import jakarta.validation.constraints.NotBlank;

public class UserRegistrationRequestDTO {

    @NotBlank(message = "The password is required.")
    private String password;
    @NotBlank(message = "The firstName is required.")
    private String firstName;

    private GenderEnumDTO gender;
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

    private AddressDTO address;

    private Double rating;

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

    public GenderEnumDTO getGender() {
        return gender;
    }

    public void setGender(GenderEnumDTO gender) {
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
