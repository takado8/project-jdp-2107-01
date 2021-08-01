package com.kodilla.ecommercee.user.controller;

public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Wrong id - does not exist!";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
