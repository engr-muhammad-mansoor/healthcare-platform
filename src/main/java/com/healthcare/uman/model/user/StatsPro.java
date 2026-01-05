package com.healthcare.uman.model.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsPro implements Serializable {

    private Integer nbConsultation;

    private Integer nbCancellation;

    private Integer nbPayment;

    private Double amountEarned;

    private Integer nbPatients;

    public Integer getNbConsultation() {
        return nbConsultation;
    }

    public void setNbConsultation(Integer nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    public Integer getNbCancellation() {
        return nbCancellation;
    }

    public void setNbCancellation(Integer nbCancellation) {
        this.nbCancellation = nbCancellation;
    }

    public Integer getNbPayment() {
        return nbPayment;
    }

    public void setNbPayment(Integer nbPayment) {
        this.nbPayment = nbPayment;
    }

    public Double getAmountEarned() {
        return amountEarned;
    }

    public void setAmountEarned(Double amountEarned) {
        this.amountEarned = amountEarned;
    }

    public Integer getNbPatients() {
        return nbPatients;
    }

    public void setNbPatients(Integer nbPatients) {
        this.nbPatients = nbPatients;
    }
}
