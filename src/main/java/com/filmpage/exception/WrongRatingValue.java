package com.filmpage.exception;

public class WrongRatingValue extends RuntimeException {

    public WrongRatingValue(String msg) {
        super(msg);
    }
}
