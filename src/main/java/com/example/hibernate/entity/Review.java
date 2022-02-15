package com.example.hibernate.entity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    //@Enumerated(EnumType.ORDINAL)
    //Ordinal is depending on a position of the enums, which is not good when you add a new enum in front
    private ReviewRating rating;

    private String description;

    @ManyToOne
    private Course course;

    protected Review() {
    }

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public ReviewRating getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nReview{" +
                "rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
