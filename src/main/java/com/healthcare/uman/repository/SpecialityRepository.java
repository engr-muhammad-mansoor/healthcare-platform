package com.healthcare.uman.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.healthcare.uman.model.speciality.Speciality;

public interface SpecialityRepository extends MongoRepository<Speciality, String> {

    Speciality findByName(String name);

    @Query("{ 'keywords': { $in: ?0 }}")
    List<Speciality> findByKeywords(List<String> keywords);

}
