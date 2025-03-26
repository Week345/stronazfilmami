package com.filmpage.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateFilmDto {
    private Long id;

    private String title;

    private String category1;

    private String category2;

    private String description;

    private BigDecimal rating;

    private String awards;

    private Integer prodYear;

    private String imageURL;

    private Boolean active;

    private Integer ratingsNumber;

    private Integer IMDBRating;
}
