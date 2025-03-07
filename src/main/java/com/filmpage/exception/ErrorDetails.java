package com.filmpage.exception;

import lombok.Data;

@Data
public class ErrorDetails {
    private int statusCode;
    private String errorMessage;
    private String status;
}
