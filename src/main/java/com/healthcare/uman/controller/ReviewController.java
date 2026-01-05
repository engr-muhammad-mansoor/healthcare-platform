package com.healthcare.uman.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.review.ReviewDTO;
import com.healthcare.uman.mapper.ReviewMapper;
import com.healthcare.uman.model.review.Review;
import com.healthcare.uman.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Review API")
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @SupervisorLogAudit
    @PostMapping
    @Operation(summary = "Create a review", description = "Create a new review and attached it to the users")
    public ResponseEntity<ReviewDTO> addReview(@Valid @RequestBody ReviewDTO review) {
        LOGGER.debug("Call addReview with informations : {}", review.toString());
        Review reviewCreated = reviewService.addReview(ReviewMapper.INSTANCE.map(review));
        return ResponseEntity.ok().body(ReviewMapper.INSTANCE.map(reviewCreated));
    }

    @SupervisorLogAudit
    @GetMapping("/users/{id}/received")
    @Operation(summary = "Get the reviews received for a user by user Id", description = "Get the reviews received for a user by user Id")
    public ResponseEntity<List<ReviewDTO>> getReviewsReceivedForUser(@PathVariable String id) {
        try {
            List<Review> reviewCreated = reviewService.getReviewsReceivedById(id);
            return ResponseEntity.ok().body(ReviewMapper.INSTANCE.map(reviewCreated));
        } catch (Exception e) {
            LOGGER.error("Failed to get user account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @SupervisorLogAudit
    @GetMapping("/users/{id}/written")
    @Operation(summary = "Get the reviews written for a user by user Id", description = "Get the reviews written for a user by user Id")
    public ResponseEntity<List<ReviewDTO>> getReviewsWrittenForUser(@PathVariable String id) {
        try {
            List<Review> reviewsWrittenById = reviewService.getReviewsWrittenById(id);
            return ResponseEntity.ok().body(ReviewMapper.INSTANCE.map(reviewsWrittenById));
        } catch (Exception e) {
            LOGGER.error("Failed to get user account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @SupervisorLogAudit
    @GetMapping("/users/{id}")
    @Operation(summary = "Get all the reviews for a user by user Id", description = "Get all the reviews for a user by user Id")
    public ResponseEntity<List<ReviewDTO>> getReviewsForUser(@PathVariable String id) {
        try {
            List<Review> reviews = reviewService.getAllReviewsById(id);
            return ResponseEntity.ok().body(ReviewMapper.INSTANCE.map(reviews));
        } catch (Exception e) {
            LOGGER.error("Failed to get user account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @SupervisorLogAudit
    @DeleteMapping("/{id}")
    @Operation(summary = "Disable a review", description = "Disable a review")
    public ResponseEntity<String> deleteReview(@PathVariable String id) {
        try {
            reviewService.deleteReview(id);
            LOGGER.debug("Review deleted");
            return ResponseEntity.ok("Review deleted successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to delete review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
