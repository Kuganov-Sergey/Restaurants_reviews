package com.example.restaurants_reviews.entity;

import com.example.restaurants_reviews.constraint.ValidPhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
    @Column(name = "name")
    private String name;

//    @NotNull
    @Column(name = "description")
    private String description;

//    @ValidPhoneNumber
//    @NotNull
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;

    @Basic
    @Column(name = "email_address")
    private String emailAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "creation_date")
    private LocalDate date;

}
