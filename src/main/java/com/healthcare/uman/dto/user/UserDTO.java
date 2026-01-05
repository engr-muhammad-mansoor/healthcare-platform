package com.healthcare.uman.dto.user;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.uman.dto.document.DocumentDTO;
import com.healthcare.uman.dto.payment.PaymentDTO;
import com.healthcare.uman.dto.review.ReviewUserDTO;
import com.healthcare.uman.validator.PasswordMatches;
import com.healthcare.uman.validator.ValidEmail;

@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordMatches
public class UserDTO implements Serializable {

    public UserEnumDTO type;
    private String id;
    private String firstName;
    private GenderEnumDTO gender;
    private String lastName;
    private String lastNameMarried;

    @ValidEmail
    private String email;
    private String username;
    private String password;
    private String matchingPassword;
    private String profile;
    private String datePassword;
    private Instant birthDate;
    private String birthCity;
    private Instant creationDate;

    private Instant updateDate;

    private AddressDTO address;

    private ReviewUserDTO reviewUser;

    private PhotoDTO photo;

    private PaymentDTO payment;

    private List<DocumentDTO> documents;

    private Boolean active;

    private UserPreferenceDTO userPreference;

    @JsonProperty("token")
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserPreferenceDTO getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(UserPreferenceDTO userPreference) {
        this.userPreference = userPreference;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public PhotoDTO getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDTO photo) {
        this.photo = photo;
    }

    public ReviewUserDTO getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(ReviewUserDTO reviewUser) {
        this.reviewUser = reviewUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserEnumDTO getType() {
        return type;
    }

    public void setType(UserEnumDTO type) {
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

}
