package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RestaurantControllerTest extends AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void getAll() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String expected = objectMapper.writeValueAsString(restaurantService.getAllRestaurants());
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        this.mockMvc.perform(get("/restaurant/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(content().json(expected));
    }

    @Test
    void descriptionByName() throws Exception {
        String expected = restaurantService.getDescriptionByName("mac");
        this.mockMvc.perform(get("/restaurant/description/{name}", "mac"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void addRestaurantByNameAndCreationDate() throws FoundationDateIsExpiredException {
        MockedStatic<LocalDate> localDateMockedStatic = mockStatic(LocalDate.class, CALLS_REAL_METHODS);
        LocalDate defaultDateNow = LocalDate.of(2012, 2, 2);
        localDateMockedStatic.when(LocalDate::now).thenReturn(defaultDateNow);
        assertThrows(FoundationDateIsExpiredException.class,
                () -> restaurantService
                        .addRestaurantByNameAndCreationDate("mac",
                                LocalDate.of(2015, 2, 2)));

        LocalDate localDateExpected = LocalDate.of(2012, 2, 2);
        restaurantService.addRestaurantByNameAndCreationDate("kfc", localDateExpected);
        assertEquals(localDateExpected, restaurantService.getCreationDateByRestaurantName("kfc"));
    }

    @Test
    void addRestaurants() throws Exception {
        RestaurantOutDTO restaurant = RestaurantOutDTO.builder()
                .description("burgers")
                .phoneNumber("absent")
                .emailAddress(null)
                .name("mmm")
                .build();
        ObjectMapper objectMapper = new JsonMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(post("/restaurant/new")
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andExpect(status().isOk());
    }

    @Test
    void updateDescription() throws Exception {
        this.mockMvc.perform(put("/restaurant/update/{name}/{description}", "mac", "description"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findRestaurantByName() throws Exception {
        RestaurantOutDTO restaurant = RestaurantOutDTO.builder()
                .id(restaurantService.getAllRestaurants().get(0).getId())
                .description("burgers")
                .phoneNumber("+79997771122")
                .emailAddress(null)
                .name("mac")
                .build();
        ObjectMapper objectMapper = new JsonMapper();
        String expected = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(get("/restaurant/{name}", "mac"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

}
