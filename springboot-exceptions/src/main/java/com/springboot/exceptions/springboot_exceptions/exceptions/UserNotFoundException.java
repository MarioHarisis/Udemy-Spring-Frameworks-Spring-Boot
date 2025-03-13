package com.springboot.exceptions.springboot_exceptions.exceptions;

// Esta es la clase de nuestra Exception propia
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message); // mensaje que lanzará
    }
}
