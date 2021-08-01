package com.kodilla.ecommercee.cart.controller;

public class CartNotFoundException extends Exception {

    private static final String MESSAGE = "Wrong id - does not exist!";

    public CartNotFoundException() {
        super(MESSAGE);
    }
}
