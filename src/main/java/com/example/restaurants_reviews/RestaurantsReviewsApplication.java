package com.example.restaurants_reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.example.restaurants_reviews.dao", "com.example.restaurants_reviews.dao"})
public class RestaurantsReviewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsReviewsApplication.class, args);
    }

}
