package com.healthcare.uman.model.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsPatient implements Serializable {

    private Integer nbConsultation;

    private Integer nbCancellation;

    private Integer nbPayment;

    private Double amountSpend;

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

    public Double getAmountSpend() {
        return amountSpend;
    }

    public void setAmountSpend(Double amountSpend) {
        this.amountSpend = amountSpend;
    }

}
