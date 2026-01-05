package com.healthcare.uman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.user.User;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);

}
