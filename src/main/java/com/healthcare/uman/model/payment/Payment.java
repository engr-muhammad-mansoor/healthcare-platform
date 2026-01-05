package com.healthcare.uman.model.payment;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.model.user.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "payment")
public class Payment implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    private List<BankInformation> ribs;

    private List<Card> cards;

    @DBRef
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BankInformation> getRibs() {
        return ribs;
    }

    public void setRibs(List<BankInformation> ribs) {
        this.ribs = ribs;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
