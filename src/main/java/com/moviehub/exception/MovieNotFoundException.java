package com.moviehub.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String msg) {
        super(msg);
    }

}
