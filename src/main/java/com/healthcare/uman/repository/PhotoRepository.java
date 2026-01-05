package com.healthcare.uman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.user.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
