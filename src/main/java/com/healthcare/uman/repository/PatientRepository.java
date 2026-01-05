package com.healthcare.uman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.user.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {

    boolean existsByUsername(String username);

    Patient findByUsername(String username);

    Patient findByEmail(String email);

}
