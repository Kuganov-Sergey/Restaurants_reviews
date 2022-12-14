package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.entity.Review;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.service.ReviewService;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest(classes = {
        RestaurantsReviewsApplication.class})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppContextTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewService reviewService;

    @BeforeAll
    void addRestaurantsAndReviewsInDB() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("mac");
        restaurant.setDescription("burgers");
        restaurantService.addRestaurant(restaurant);
        Review review = new Review();
        review.setReview("best place");
        review.setRestaurant_id(restaurantService.getAllRestaurants().get(0));
        review.setRating(5);
        reviewService.addReview(review);
    }

    @BeforeEach
    void setDefaultParameters() {
        restaurantService.addPhoneByRestaurantName("mac", "+79997771122");
    }

    @AfterAll
    void cleanTable() {
        restaurantRepository.deleteAll();
    }

    @Test
    void findRestaurantByName() throws Exception {
        String name = "mac";
        String newName = restaurantService.findRestaurantByName(name).getName();
        assertEquals(name, newName);
    }

    @Test
    void getAllRestaurants() {
        assertNotNull(restaurantService.getAllRestaurants());
        assertEquals("mac", restaurantService.getAllRestaurants().get(0).getName());
        assertEquals("burgers", restaurantService.getAllRestaurants().get(0).getDescription());
        assertEquals("+79997771122", restaurantService.getAllRestaurants().get(0).getPhoneNumber());
    }

    @Test
    void updateDescriptionByName() {
        String newDescription = "best burgers";
        restaurantService.updateDescriptionByName("mac", newDescription);
        assertEquals(newDescription, restaurantService.getDescriptionByName("mac"));
        restaurantService.updateDescriptionByName("mac", "burgers");
    }


    @Test
    void addPhoneByRestaurantName() {
        String phone = "+79998887766";
        restaurantService.addPhoneByRestaurantName("mac", phone);
        assertEquals(phone, restaurantService.findRestaurantByName("mac").getPhoneNumber());
    }


    @Test
    void getReviewsByRestaurantName() throws Exception {
        String review = "best place";
        System.out.println(reviewService.getReviewsByRestaurantName("mac"));
        assertEquals(review, reviewService.getReviewsByRestaurantName("mac").get(0));
    }

    @Test
    void getRatingByRestaurantName() throws Exception {
        int expectedRating = 5;
        assertEquals(expectedRating, reviewService.getRatingByRestaurantName("mac"));
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
    void updateReviewByRestaurantId() {
        String expectedReview = "best burgers";
        reviewService.updateReviewByRestaurantId(restaurantService.getAllRestaurants().get(0).getId(), expectedReview);
        assertEquals(expectedReview, reviewService.getReviewsByRestaurantName("mac").get(0));

    }

}