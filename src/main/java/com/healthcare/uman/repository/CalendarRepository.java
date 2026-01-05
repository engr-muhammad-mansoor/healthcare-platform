package com.healthcare.uman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.healthcare.uman.model.booking.Calendar;

public interface CalendarRepository extends MongoRepository<Calendar, String> {

    @Query("{'idUser.id': ?0}")
    Calendar findByIdUser(String idUser);

}
