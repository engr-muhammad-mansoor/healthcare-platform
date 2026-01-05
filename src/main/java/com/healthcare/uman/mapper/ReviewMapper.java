package com.healthcare.uman.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.healthcare.uman.dto.review.ReviewDTO;
import com.healthcare.uman.model.review.Review;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review map(final ReviewDTO reviewDTO);

    ReviewDTO map(final Review review);

    List<ReviewDTO> map(List<Review> reviews);

}
