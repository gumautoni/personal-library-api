package com.gumautoni.personallibraryapi.exception;

public class LivroNotFoundException extends RuntimeException {

    public LivroNotFoundException(String message) {
        super(message);
    }
}