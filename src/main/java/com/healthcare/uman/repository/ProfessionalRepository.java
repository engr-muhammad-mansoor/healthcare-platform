package com.healthcare.uman.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.healthcare.uman.model.user.Professional;

public interface ProfessionalRepository extends MongoRepository<Professional, String> {

    boolean existsByUsername(String username);

    Professional findByUsername(String username);

    Professional findByEmail(String email);

    @Query("{ $or: [ " +
            "{ 'professionalCard.specialities.speciality': { $in: ?0 } }, " +
            "{ 'professionalCard.humanAbilities.preference': { $in: ?1 } } ] }")
    List<Professional> findBySpecialityIdsAndPreferences(List<String> specialityIds, List<String> preferences);

    @Query("{ 'professionalCard.specialities.speciality': { $in: ?0 } }}")
    List<Professional> findBySpecialityIds(List<String> specialityIds);
}
