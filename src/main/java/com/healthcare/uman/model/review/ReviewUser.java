package com.healthcare.uman.model.review;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewUser implements Serializable {

    private Double rating;
    private Integer totalReviewsReceived;

    private Float globalRateAverage;
    private Integer globalCount;
    private List<LocalRate> localRateAverages;

    @DBRef
    private List<Review> reviewsWritten = new ArrayList<>();

    @DBRef
    private List<Review> reviewsReceived = new ArrayList<>();

    public Integer getTotalReviewsReceived() {
        return totalReviewsReceived;
    }

    public void setTotalReviewsReceived(Integer totalReviewsReceived) {
        this.totalReviewsReceived = totalReviewsReceived;
    }

    public Float getGlobalRateAverage() {
        return globalRateAverage;
    }

    public void setGlobalRateAverage(Float globalRateAverage) {
        this.globalRateAverage = globalRateAverage;
    }

    public Integer getGlobalCount() {
        return globalCount;
    }

    public void setGlobalCount(Integer globalCount) {
        this.globalCount = globalCount;
    }

    public List<LocalRate> getLocalRateAverages() {
        return localRateAverages;
    }

    public void setLocalRateAverages(List<LocalRate> localRateAverages) {
        this.localRateAverages = localRateAverages;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Review> getReviewsWritten() {
        return reviewsWritten;
    }

    public void setReviewsWritten(List<Review> reviewsWritten) {
        this.reviewsWritten = reviewsWritten;
    }

    public List<Review> getReviewsReceived() {
        return reviewsReceived;
    }

    public void setReviewsReceived(List<Review> reviewsReceived) {
        this.reviewsReceived = reviewsReceived;
    }
}
