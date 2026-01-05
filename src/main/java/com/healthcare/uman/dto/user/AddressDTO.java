package com.healthcare.uman.dto.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO implements Serializable {

    private String adresseName;
    private String phone;
    private String numberStreet;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    // A professional can disabled address
    private boolean disabled;

    private String usefulInformations;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(String numberStreet) {
        this.numberStreet = numberStreet;
    }

    public String getAdresseName() {
        return adresseName;
    }

    public void setAdresseName(String adresseName) {
        this.adresseName = adresseName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getUsefulInformations() {
        return usefulInformations;
    }

    public void setUsefulInformations(String usefulInformations) {
        this.usefulInformations = usefulInformations;
    }
}
