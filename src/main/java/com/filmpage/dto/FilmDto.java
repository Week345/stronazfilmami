package com.filmpage.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FilmDto {

    private Long id;

    private String title;

    private String category;

    private String description;

    private BigDecimal rating;

    private String awards;

    private Integer prodYear;

    private byte[] image;

}
