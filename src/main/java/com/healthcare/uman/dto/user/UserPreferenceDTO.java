package com.healthcare.uman.dto.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPreferenceDTO implements Serializable {

    private boolean displayHistory = true;

    private boolean displayDocuments = true;

    private boolean displayMap = true;

    private CookieDTO cookie;

    private boolean displayNotes = true;

    public boolean isDisplayHistory() {
        return displayHistory;
    }

    public void setDisplayHistory(boolean displayHistory) {
        this.displayHistory = displayHistory;
    }

    public boolean isDisplayDocuments() {
        return displayDocuments;
    }

    public void setDisplayDocuments(boolean displayDocuments) {
        this.displayDocuments = displayDocuments;
    }

    public boolean isDisplayMap() {
        return displayMap;
    }

    public void setDisplayMap(boolean displayMap) {
        this.displayMap = displayMap;
    }

    public CookieDTO getCookie() {
        return cookie;
    }

    public void setCookie(CookieDTO cookie) {
        this.cookie = cookie;
    }

    public boolean isDisplayNotes() {
        return displayNotes;
    }

    public void setDisplayNotes(boolean displayNotes) {
        this.displayNotes = displayNotes;
    }
}
