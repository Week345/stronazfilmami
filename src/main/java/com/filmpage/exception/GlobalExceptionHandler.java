package com.filmpage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FilmNotFound.class)
    public ResponseEntity<ErrorDetails> FilmNotFound(FilmNotFound fnf) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.name());
        errorDetails.setErrorMessage(fnf.getMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(WrongRatingValue.class)
    public ResponseEntity<ErrorDetails> WrongRatingValue(WrongRatingValue wrv) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.name());
        errorDetails.setErrorMessage(wrv.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FilmVariableNull.class)
    public ResponseEntity<ErrorDetails> FilmVariableNull(FilmVariableNull fvn) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.name());
        errorDetails.setErrorMessage(fvn.getMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
