package com.healthcare.uman.dto.review;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewUserDTO implements Serializable {

    private Double rating;
    private Integer totalReviewsReceived;

    private Float globalRateAverage;
    private Integer globalCount;
    private List<LocalRateDTO> localRateAverages;

    private List<ReviewDTO> reviewsWritten = new ArrayList<>();

    private List<ReviewDTO> reviewsReceived = new ArrayList<>();

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

    public List<LocalRateDTO> getLocalRateAverages() {
        return localRateAverages;
    }

    public void setLocalRateAverages(List<LocalRateDTO> localRateAverages) {
        this.localRateAverages = localRateAverages;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<ReviewDTO> getReviewsWritten() {
        return reviewsWritten;
    }

    public void setReviewsWritten(List<ReviewDTO> reviewsWritten) {
        this.reviewsWritten = reviewsWritten;
    }

    public List<ReviewDTO> getReviewsReceived() {
        return reviewsReceived;
    }

    public void setReviewsReceived(List<ReviewDTO> reviewsReceived) {
        this.reviewsReceived = reviewsReceived;
    }
}
