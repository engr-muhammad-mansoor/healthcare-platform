package com.healthcare.uman.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.healthcare.uman.model.booking.Booking;
import com.healthcare.uman.model.user.User;

public interface BookingRepository extends MongoRepository<Booking, String> {

    @Query("{'patientId.id': ?0}")
    List<Booking> findByPatientId(User patientId);

    @Query("{'professionalId.id': ?0}")
    List<Booking> findByProfessionalId(User professionalId);

    List<Booking> findByPatientIdAndProfessionalIdAndActiveIsTrue(User patient, User professional, boolean active);

}
