package com.example.restaurants_reviews.entity;

import com.example.restaurants_reviews.constraint.ValidPhoneNumber;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

//    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @ValidPhoneNumber
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;

    @Basic
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "creation_date")
    private LocalDate date;

}
