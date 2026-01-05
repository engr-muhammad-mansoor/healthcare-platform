package com.healthcare.uman.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.review.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByIdAuthor(String idAuthor);

    List<Review> findByIdReceiver(String idReceiver);

    List<Review> findByIdReceiverOrIdAuthor(String idReceiver, String idAuthor);

}
