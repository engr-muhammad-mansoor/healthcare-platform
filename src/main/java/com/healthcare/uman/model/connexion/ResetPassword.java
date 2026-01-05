package com.healthcare.uman.model.connexion;

import com.healthcare.uman.validator.PasswordMatches;

@PasswordMatches
public class ResetPassword {
    private String email;
    private String password;
    private String matchingPassword;

    public ResetPassword() {
    }

    public ResetPassword(String email, String password, String matchingPassword) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}