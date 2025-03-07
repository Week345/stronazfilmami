package com.filmpage.exception;

public class FilmNotFound extends RuntimeException{

    public FilmNotFound(String msg) {
        super(msg);
    }
}
