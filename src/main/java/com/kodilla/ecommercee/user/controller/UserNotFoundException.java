package com.kodilla.ecommercee.user.controller;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
