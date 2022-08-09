package com.example.restaurants_reviews.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant_id;

    @Column(name = "review")
    private String review;

    @Column(name = "rating")
    private int rating;
}
