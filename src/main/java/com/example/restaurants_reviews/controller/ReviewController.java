package com.example.restaurants_reviews.controller;
;
import com.example.restaurants_reviews.entity.Review;
import com.example.restaurants_reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{name}")
    public List<String> getReviewsByName(@PathVariable String name) {
        return reviewService.getReviewsByRestaurantName(name);
    }

    @GetMapping("/rating/{name}")
    public double getRatingByName(@PathVariable String name) {
        return reviewService.getRatingByRestaurantName(name);
    }

    @PostMapping("/new")
    public void addReview(@RequestBody Review review) {
        reviewService.addReview(review);
    }
}
