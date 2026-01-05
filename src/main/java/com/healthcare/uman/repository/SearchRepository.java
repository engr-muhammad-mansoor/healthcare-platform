package com.healthcare.uman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.review.Review;

public interface SearchRepository extends MongoRepository<Review, String> {

}
