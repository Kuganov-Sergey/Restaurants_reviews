package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantOutDTO restaurantToRestaurantOutDTO(Restaurant restaurant);
}
