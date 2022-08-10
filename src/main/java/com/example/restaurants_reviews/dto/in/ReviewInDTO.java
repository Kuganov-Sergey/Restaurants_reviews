package com.example.restaurants_reviews.dto.in;

import com.example.restaurants_reviews.entity.Restaurant;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewInDTO {

    @NotBlank(message = "You cant create review with empty reference id field")
    private Restaurant restaurant_id;

    @NotBlank(message = "Empty review!")
    private String review;

    private int rating;
}
