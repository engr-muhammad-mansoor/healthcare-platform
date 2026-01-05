package com.healthcare.uman.model.user;

import com.healthcare.uman.model.conversation.Conversation;
import com.healthcare.uman.model.document.Document;
import com.healthcare.uman.model.payment.Payment;
import com.healthcare.uman.model.review.ReviewUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable {

    public UserEnum type;
    @Id
    private String id;
    private String firstName;
    private GenderEnum gender;
    private String lastName;
    private String lastNameMarried;
    private ProviderEnum providerEnum;
    @Size(max = 50)
    @Email
    private String email;
    private String username;
    @Size(max = 120)
    private String password;
    private String profile;
    private String datePassword;
    private Instant birthDate;
    private String birthCity;
    private String provider;

    public String getProvider() {
        return provider;
    }

    private Instant creationDate;

    private Instant updateDate;

    private Address address;

    private ReviewUser reviewUser;

    @DBRef
    private Photo photo;

    @DBRef
    private Payment payment;

    @DBRef
    private List<Document> documents;

    private Boolean active;

    private UserPreference userPreference;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    @DBRef
    private List<Conversation> conversations;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addConversation(Conversation conversation) {
        if (this.conversations == null) {
            this.conversations = new ArrayList<>();
        }
        this.conversations.add(conversation);
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserPreference getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(UserPreference userPreference) {
        this.userPreference = userPreference;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ProviderEnum getProviderEnum() {
        return providerEnum;
    }

    public void setProviderEnum(ProviderEnum providerEnum) {
        this.providerEnum = providerEnum;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public ReviewUser getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(ReviewUser reviewUser) {
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

    public UserEnum getType() {
        return type;
    }

    public void setType(UserEnum type) {
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

    public void setProvider(String s) {
        this.provider = s;
    }
}
