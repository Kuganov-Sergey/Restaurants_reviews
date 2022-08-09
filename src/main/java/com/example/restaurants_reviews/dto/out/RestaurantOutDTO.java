package com.example.restaurants_reviews.dto.out;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantOutDTO {

    private int id;
    private String name;
    private String description;
    private String emailAddress;
    private String phoneNumber;

    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate date;

}
