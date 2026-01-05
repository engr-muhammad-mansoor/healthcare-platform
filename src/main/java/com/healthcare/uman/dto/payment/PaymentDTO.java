package com.healthcare.uman.dto.payment;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO implements Serializable {

    private String id = UUID.randomUUID().toString();

    private List<BankInformationDTO> ribs;

    private List<CardDTO> cards;

    private String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BankInformationDTO> getRibs() {
        return ribs;
    }

    public void setRibs(List<BankInformationDTO> ribs) {
        this.ribs = ribs;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
