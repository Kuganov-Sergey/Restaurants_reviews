package com.example.restaurants_reviews.service;

import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.IncorrectEmailAddressException;
import com.example.restaurants_reviews.util.EmailUtil;
import com.example.restaurants_reviews.util.PhoneUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public String getDescriptionByName(String name) {
        Restaurant restaurant = findRestaurantByName(name);
        return restaurant.getDescription();
    }

    @Override
    @Transactional
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        String phone = restaurant.getPhoneNumber();
        if (phone == null || phone.equals("absent")) {
            restaurant.setPhoneNumber("absent");
        } else {
            try {
                restaurant.setPhoneNumber(PhoneUtil.reformatRuTelephone(phone));
            } catch (NumberParseException e) {
                e.printStackTrace();
            }
        }
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateDescriptionByName(String name, String description) {
        Restaurant restaurant = findRestaurantByName(name);
        restaurant.setDescription(description);
        addRestaurant(restaurant);
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        return restaurantRepository.findRestaurantByName(name);
    }

    @Override
    public void addPhoneByRestaurantName(String name, String phone) {
        Restaurant restaurant = restaurantRepository.findRestaurantByName(name);
        try {
            restaurant.setPhoneNumber(PhoneUtil.reformatRuTelephone(phone));
            restaurantRepository.save(restaurant);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmailAddressByName(String name, String emailAddress) {
        Restaurant restaurant = restaurantRepository.findRestaurantByName(name);
        if (EmailUtil.checkValid(emailAddress)) {
            restaurant.setEmailAddress(emailAddress);
            restaurantRepository.save(restaurant);
        } else {
            try {
                throw new IncorrectEmailAddressException("write correct Email Address");
            } catch (IncorrectEmailAddressException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addRestaurantByNameAndCreationDate(String name, LocalDate creationDate) throws FoundationDateIsExpiredException {
        if (creationDate == null || LocalDate.now().isBefore(creationDate)) {
            throw new FoundationDateIsExpiredException(name, creationDate);
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription("totory");
        restaurant.setEmailAddress("default@mail.ru");
        restaurant.setPhoneNumber("absent");
        restaurant.setDate(creationDate);
        restaurantRepository.save(restaurant);
    }

    @Override
    public LocalDate getCreationDateByRestaurantName(String name) {
        Restaurant restaurant = findRestaurantByName(name);
        return restaurant.getDate();
    }
}
