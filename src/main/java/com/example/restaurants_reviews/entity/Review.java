package com.example.restaurants_reviews.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return id == review1.id && rating == review1.rating && Objects.equals(restaurant_id, review1.restaurant_id) && Objects.equals(review, review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurant_id, review, rating);
    }
}
