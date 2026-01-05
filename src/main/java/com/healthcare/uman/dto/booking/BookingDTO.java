package com.healthcare.uman.dto.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.dto.payment.PaymentInformationDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO implements Serializable {

    private String id;

    /**
     * ID of the patient
     */
    private String patientId;

    /**
     * ID of the professional
     */
    private String professionalId;

    /**
     * Date when the user's booked
     */
    private Date createdDate;

    /**
     * Date when the user has modified his booking
     */
    private Date modifiedDate;

    /**
     * Starting date of the appointment
     */
    private LocalDate date;
    private SlotDTO slots;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateEnd;

    private String bookingType;

    /**
     * Status of the booking (canceled)
     */
    private String status;

    /**
     * Price
     */
    private Double price;

    private PaymentInformationDTO paymentInformation;

    private String documents;

    private boolean alertEarlier;

    /**
     * Is the appointment active
     */
    private Boolean active;

    /**
     * Id of the conversation to reach to this appointment
     */
    private String idSearching;

    public String getIdSearching() {
        return idSearching;
    }

    public void setIdSearching(String idSearching) {
        this.idSearching = idSearching;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SlotDTO getSlots() {
        return slots;
    }

    public void setSlots(SlotDTO slots) {
        this.slots = slots;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PaymentInformationDTO getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformationDTO paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public boolean isAlertEarlier() {
        return alertEarlier;
    }

    public void setAlertEarlier(boolean alertEarlier) {
        this.alertEarlier = alertEarlier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
