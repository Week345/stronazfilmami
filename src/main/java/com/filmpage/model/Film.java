package com.filmpage.model;

import com.filmpage.exception.WrongRatingValue;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "films")
@Data
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    private String description;

    private String awards;

    private Integer prodYear;

    @Getter
    private Integer ratingsNumber;
    @Getter
    private Integer ratingSum;
    @Getter
    private BigDecimal rating;

    public void addRating(int rating) {
        if (ratingsNumber == null) {
            ratingsNumber = 1;
        } else {
            ratingsNumber += 1;
        }
        if (ratingSum == null) {
            ratingSum = rating;
        } else {
            ratingSum += rating;
        }
        if (rating <= 0 || rating > 5) {
            throw new WrongRatingValue("Ocena musi mieć wartość od 1 do 5");
        }

        BigDecimal ratingSumBD = BigDecimal.valueOf(ratingSum);
        BigDecimal ratingsNumberBD = BigDecimal.valueOf(ratingsNumber);
        this.rating = ratingSumBD.divide(ratingsNumberBD, RoundingMode.HALF_UP);
        this.rating = this.rating.setScale(2, RoundingMode.HALF_UP);
    }

}
