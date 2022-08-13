package com.example.restaurants_reviews.service;

import com.example.restaurants_reviews.entity.Review;

import java.util.List;

public interface ReviewService {

    List<String> getReviewsByRestaurantName(String name);

    double getRatingByRestaurantName(String name);

    void addReview(Review review);

    void updateReviewByRestaurantId(long id, String review);
}
