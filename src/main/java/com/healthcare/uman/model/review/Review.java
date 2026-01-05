package com.healthcare.uman.model.review;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "review")
public class Review implements Serializable {

    @Id
    private String id;

    @NotBlank(message = "The id author is required.")
    private String idAuthor;

    @NotBlank(message = "The title review is required.")
    private String titleReview;

    @NotBlank(message = "The review is required.")
    private String review;

    @NotBlank(message = "The id receiver is required.")
    private String idReceiver;

    @NotBlank(message = "The rating is required.")
    private Integer rating;

    private Date date;

    public String getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(String idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleReview() {
        return titleReview;
    }

    public void setTitleReview(String titleReview) {
        this.titleReview = titleReview;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(String idReceiver) {
        this.idReceiver = idReceiver;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
