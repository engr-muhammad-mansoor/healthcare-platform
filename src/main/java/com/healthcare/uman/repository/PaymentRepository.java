package com.healthcare.uman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.payment.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
