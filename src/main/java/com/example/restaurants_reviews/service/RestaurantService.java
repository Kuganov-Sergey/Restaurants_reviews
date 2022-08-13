package com.example.restaurants_reviews.service;


import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.IncorrectEmailAddressException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    String getDescriptionByName(String name) throws RestaurantNotFoundException;
    List<Restaurant> getAllRestaurants();
    long addRestaurant(Restaurant restaurant);
    void updateDescriptionByName(String name, String description);
    Restaurant findRestaurantByName(String name);
    void addPhoneByRestaurantName(String name, String phone);
    void addEmailAddressByName(String name, String emailAddress) throws FoundationDateIsExpiredException, IncorrectEmailAddressException;
    long addRestaurantByNameAndCreationDate(String name, LocalDate creationDate) throws FoundationDateIsExpiredException;
    LocalDate getCreationDateByRestaurantName(String name);
}
