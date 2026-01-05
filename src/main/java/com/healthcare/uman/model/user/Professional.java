package com.healthcare.uman.model.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.model.payment.BankInformation;
import com.healthcare.uman.model.payment.SubscriptionEnum;
import com.healthcare.uman.model.professional.ProfessionalCard;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "user")
public class Professional extends User {

    @DBRef
    private Calendar calendars;

    private SubscriptionEnum subscriptionEnum;

    private BankInformation bankInformation;

    private ProfessionalCard professionalCard;

    public ProfessionalCard getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(ProfessionalCard professionalCard) {
        this.professionalCard = professionalCard;
    }

    public Calendar getCalendars() {
        return calendars;
    }

    public void setCalendars(Calendar calendars) {
        this.calendars = calendars;
    }

    public SubscriptionEnum getSubscriptionEnum() {
        return subscriptionEnum;
    }

    public void setSubscriptionEnum(SubscriptionEnum subscriptionEnum) {
        this.subscriptionEnum = subscriptionEnum;
    }

    public BankInformation getBankInformation() {
        return bankInformation;
    }

    public void setBankInformation(BankInformation bankInformation) {
        this.bankInformation = bankInformation;
    }

    public BankInformation updateBankInformation(BankInformation updatedBankInfo) {
        if (updatedBankInfo != null) {
            if (updatedBankInfo.getIban() != null) {
                this.bankInformation.setIban(updatedBankInfo.getIban());
            }
            if (updatedBankInfo.getBic() != null) {
                this.bankInformation.setBic(updatedBankInfo.getBic());
            }
            if (updatedBankInfo.getBankName() != null) {
                this.bankInformation.setBankName(updatedBankInfo.getBankName());
            }
        }
        return bankInformation;
    }

}
