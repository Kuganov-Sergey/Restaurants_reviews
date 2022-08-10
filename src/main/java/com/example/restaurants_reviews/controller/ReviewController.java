package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.mapper.ReviewMapper;
import com.example.restaurants_reviews.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }


    @GetMapping("/{name}")
    public List<String> getReviewsByName(@PathVariable String name) {
        return reviewService.getReviewsByRestaurantName(name);
    }

    @GetMapping("/rating/{name}")
    public double getRatingByName(@PathVariable String name) {
        return reviewService.getRatingByRestaurantName(name);
    }

    @PostMapping("/new")
    public void addReview(@RequestBody ReviewInDTO reviewInDTO) {
        reviewService.addReview(reviewMapper.reviewInDTOToReviewEntity(reviewInDTO));
    }
}
