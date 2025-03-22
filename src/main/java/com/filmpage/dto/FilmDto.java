package com.filmpage.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FilmDto {

    private Long id;

    private String title;

    private List<CategoryDto> categories;

    private String description;

    private BigDecimal rating;

    private String awards;

    private Integer prodYear;

    private byte[] image;
    
    private String imageType;

    private Integer ratingsNumber;

}
