package com.healthcare.uman.model.booking;

import com.healthcare.uman.model.payment.PaymentInformation;
import com.healthcare.uman.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Document(collection = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {

    @Id
    private final String id = UUID.randomUUID().toString();

    @DBRef
    private User patientId;

    @DBRef
    private User professionalId;

    /**
     * Date when the user's booked
     */
    private Date createdDate;

    /**
     * Date when the user has modified his booking
     */
    private Date modifiedDate;

    private LocalDate date;

    private Slot slots;

    /**
     * Date of the appointment beginning
     */
    private Date dateEnd;

    private BookingEnum bookingType;

    private BookingStatusEnum status;

    private Double price;

    private PaymentInformation paymentInformation;

    private String documents;

    private boolean alertEarlier;

    private Boolean active;
}
