package com.healthcare.uman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.model.review.LocalRate;
import com.healthcare.uman.model.review.Review;
import com.healthcare.uman.model.review.ReviewUser;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.ReviewRepository;
import com.healthcare.uman.repository.UserRepository;

@Service
public class ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    /**
     * Add a new review
     *
     * @param review
     *
     * @return
     */
    public Review addReview(Review review) {
        LOGGER.debug("Start addReview with informations : {}", review.toString());
        final Review reviewSaved = reviewRepository.save(review);

        Optional<User> receiverOptional = userRepository.findById(review.getIdReceiver());
        Optional<User> authorOptional = userRepository.findById(review.getIdAuthor());

        if (authorOptional.isPresent() && receiverOptional.isPresent()) {
            LOGGER.debug("Author existing : {}", review.getIdAuthor());
            LOGGER.debug("Receiver existing : {}", review.getIdReceiver());

            // Retrieve author user to add review on it
            User author = authorOptional.get();
            if (author.getReviewUser() == null) {
                ReviewUser reviewUser = new ReviewUser();
                author.setReviewUser(reviewUser);
            }

            List<Review> reviewsWritten = author.getReviewUser().getReviewsWritten();
            reviewsWritten.add(reviewSaved);
            author.getReviewUser().setReviewsWritten(reviewsWritten);
            userRepository.save(author);

            LOGGER.debug("Reviews on the author updated : {}", review.getIdAuthor());

            // Retrieve receiver user to add review on it
            User receiver = receiverOptional.get();
            if (receiver.getReviewUser() == null) {
                ReviewUser reviewUser = new ReviewUser();
                receiver.setReviewUser(reviewUser);
            }
            List<Review> reviewsReceived = receiver.getReviewUser().getReviewsReceived();
            reviewsReceived.add(reviewSaved);
            receiver.getReviewUser().setReviewsReceived(reviewsReceived);
            processRatingCustomer(receiver.getReviewUser());
            userRepository.save(receiver);

            LOGGER.debug("Reviews on the received updated : {}", review.getIdReceiver());
        }
        return reviewSaved;
    }

    /**
     * Get the reviews by user's id / written and received
     *
     * @param id
     */
    public List<Review> getAllReviewsById(String id) throws UserNotFoundException {
        LOGGER.debug("getAllReviewsById for user Id : {}", id);

        List<Review> allReviews = new ArrayList<>();
        List<Review> reviewsWritten = reviewRepository.findByIdAuthor(id);
        List<Review> reviewsReceived = reviewRepository.findByIdReceiver(id);
        if (reviewsWritten != null) {
            allReviews.addAll(reviewsReceived);
            allReviews.addAll(reviewsWritten);
            LOGGER.debug("getAllReviewsById for user : {} - reviews existing", id);
            return allReviews;
        }
        throw new UserNotFoundException("Aucune review n'existe");
    }

    /**
     * Get the reviews received by user's id
     *
     * @param id
     */
    public List<Review> getReviewsReceivedById(String id) throws UserNotFoundException {
        LOGGER.debug("getReviewsReceivedById for user Id : {}", id);

        List<Review> reviewsReceived = reviewRepository.findByIdReceiver(id);
        if (reviewsReceived != null) {
            LOGGER.debug("getReviewsReceivedById for user : {} - reviews existing", id);
            return reviewsReceived;
        }
        throw new UserNotFoundException("Aucune review n'existe");
    }

    /**
     * Get the reviews written by user's id
     *
     * @param id
     */
    public List<Review> getReviewsWrittenById(String id) throws UserNotFoundException {
        LOGGER.debug("getReviewsWrittenById for user Id : {}", id);

        List<Review> reviewsWritten = reviewRepository.findByIdAuthor(id);
        if (reviewsWritten != null) {
            LOGGER.debug("getReviewsWrittenById for user : {} - reviews existing", id);
            return reviewsWritten;
        }
        throw new UserNotFoundException("Aucune review n'existe");
    }

    /**
     * Process of rating's customer
     *
     * @param reviewUser
     */
    public void processRatingCustomer(ReviewUser reviewUser) {
        LOGGER.debug("processRatingCustomer for the review");

        // Retrieve all reviews for the receiver
        final List<Review> reviews = reviewUser.getReviewsReceived();
        if (!reviews.isEmpty()) {
            Integer totalAvis = reviews.size();
            reviewUser.setTotalReviewsReceived(totalAvis);
            int sumTotalAvis = 0;

            final Map<Integer, List<Review>> avisCollect = reviews.stream().collect(Collectors.groupingBy(Review::getRating));
            List<LocalRate> localRates = new ArrayList<>();

            if (totalAvis > 0) {
                for (int i = 1; i <= 5; i++) {
                    List<Review> listAvis = avisCollect.get(i);
                    int avisInt = (listAvis == null) ? 0 : listAvis.size();
                    LocalRate localRate = new LocalRate();
                    localRate.createLocalRate(i, avisInt, totalAvis);
                    localRates.add(localRate);
                    if (listAvis != null) {
                        sumTotalAvis += listAvis.stream().mapToInt(Review::getRating).sum();
                    }
                }
            }
            reviewUser.setLocalRateAverages(localRates);
            Double globalRateAverage = (double) sumTotalAvis / totalAvis;
            reviewUser.setGlobalRateAverage(globalRateAverage.floatValue());
        }
    }

    /**
     * Delete a review by ID
     *
     * @param id
     */
    public void deleteReview(String id) {
        final Optional<Review> reviewToDelete = reviewRepository.findById(id);
        if (reviewToDelete.isPresent()) {
            reviewRepository.delete(reviewToDelete.get());
        }
    }

}
