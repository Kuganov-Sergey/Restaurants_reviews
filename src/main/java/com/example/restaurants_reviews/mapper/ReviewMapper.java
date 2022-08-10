package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewOutDTO reviewToReviewOutDTO(Review review);

    @Mapping(target = "id", ignore = true)
    Review reviewInDTOToReviewEntity(ReviewInDTO reviewInDTO);
}
