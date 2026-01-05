package com.healthcare.uman.dto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.dto.booking.CalendarDTO;
import com.healthcare.uman.dto.payment.BankInformationDTO;
import com.healthcare.uman.dto.payment.SubscriptionEnumDTO;
import com.healthcare.uman.dto.professional.ProfessionalCardDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessionalDTO extends UserDTO {

    private CalendarDTO calendarDTO;

    private SubscriptionEnumDTO subscriptionEnum;

    private BankInformationDTO bankInformation;

    private ProfessionalCardDTO professionalCard;

    public ProfessionalCardDTO getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(ProfessionalCardDTO professionalCard) {
        this.professionalCard = professionalCard;
    }

    public CalendarDTO getCalendarDTO() {
        return calendarDTO;
    }

    public void setCalendarDTO(CalendarDTO calendarDTO) {
        this.calendarDTO = calendarDTO;
    }

    public SubscriptionEnumDTO getSubscriptionEnum() {
        return subscriptionEnum;
    }

    public void setSubscriptionEnum(SubscriptionEnumDTO subscriptionEnum) {
        this.subscriptionEnum = subscriptionEnum;
    }

    public BankInformationDTO getBankInformation() {
        return bankInformation;
    }

    public void setBankInformation(BankInformationDTO bankInformation) {
        this.bankInformation = bankInformation;
    }

    public BankInformationDTO updateBankInformation(BankInformationDTO updatedBankInfo) {
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


    /**
     * Clears sensitive data from a single professional DTO.
     **/
    public void clearSensitiveDataForProfessional() {
        this.setSubscriptionEnum(null);
        this.setBankInformation(null);
        this.setType(null);
        this.setEmail(null);
        this.setPassword(null);
        this.setUsername(null);
        this.setDatePassword(null);
        this.setBirthDate(null);
        this.setBirthCity(null);
        this.setCreationDate(null);
        this.setUpdateDate(null);
        this.setPayment(null);
        this.setDocuments(null);
        this.setUserPreference(null);
    }

}
