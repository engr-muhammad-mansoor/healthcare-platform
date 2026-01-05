package com.healthcare.uman.model.payment;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    private String cardNumber;

    private String cardName;

    private String cVC;

    private Date cardDateExpiration;

    private boolean defaultCard = true;

    public boolean isDefaultCard() {
        return defaultCard;
    }

    public void setDefaultCard(boolean defaultCard) {
        this.defaultCard = defaultCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getcVC() {
        return cVC;
    }

    public void setcVC(String cVC) {
        this.cVC = cVC;
    }

    public Date getCardDateExpiration() {
        return cardDateExpiration;
    }

    public void setCardDateExpiration(Date cardDateExpiration) {
        this.cardDateExpiration = cardDateExpiration;
    }
}
