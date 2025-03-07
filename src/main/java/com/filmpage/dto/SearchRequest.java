package com.filmpage.dto;

import lombok.Data;

@Data
public class SearchRequest {
    private String category;

    private String searchQuery;
}
