package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.mapper.RestaurantMapper;
import com.example.restaurants_reviews.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/description/{name}")
    public String getDescriptionByName(@PathVariable String name) {
        Restaurant restaurant = restaurantService.findRestaurantByName(name);
        return restaurant.getDescription();
    }

    @PostMapping("/new")
    public RestaurantInDTO addRestaurant(@RequestBody @Valid RestaurantInDTO restaurantInDTO) {
        restaurantService.addRestaurant(restaurantMapper.restaurantInDTOToRestaurantEntity(restaurantInDTO));
        return restaurantInDTO;
    }

    @PutMapping("/update/{name}/{description}")
    public void updateDescriptionByName(@PathVariable String name, @PathVariable String description) {
        restaurantService.updateDescriptionByName(name, description);
    }

    @GetMapping("/{name}")
    public RestaurantOutDTO findRestaurantByName(@PathVariable String name) {
        Restaurant restaurant = restaurantService.findRestaurantByName(name);
        return restaurantMapper.restaurantToRestaurantOutDTO(restaurant);
    }

    @PutMapping("/newByNameAndDate/{name}/{date}")
    public void addRestaurantByNameAndCreationDate(@PathVariable String name, @PathVariable LocalDate date)
            throws FoundationDateIsExpiredException {
        restaurantService.addRestaurantByNameAndCreationDate(name, date);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleArgumentFormatException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
