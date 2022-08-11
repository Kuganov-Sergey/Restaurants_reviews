package com.example.restaurants_reviews.service;


import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.IncorrectEmailAddressException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    String getDescriptionByName(String name);
    List<Restaurant> getAllRestaurants();
    void addRestaurant(Restaurant restaurant);
    void updateDescriptionByName(String name, String description);
    Restaurant findRestaurantByName(String name);
    void addPhoneByRestaurantName(String name, String phone);
    void addEmailAddressByName(String name, String emailAddress) throws FoundationDateIsExpiredException, IncorrectEmailAddressException;
    void addRestaurantByNameAndCreationDate(String name, LocalDate creationDate) throws FoundationDateIsExpiredException;
    LocalDate getCreationDateByRestaurantName(String name);
}
