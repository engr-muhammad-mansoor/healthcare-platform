package com.healthcare.uman.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.healthcare.uman.model.professional.HumanPreference;

public interface HumanPreferencesRepository extends MongoRepository<HumanPreference, String> {

    HumanPreference findByName(String name);

    @Query("{ 'name': { $in: ?0 }}")
    List<HumanPreference> findByNames(List<String> name);
}
