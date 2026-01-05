package com.healthcare.uman.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.user.ERole;
import com.healthcare.uman.model.user.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}