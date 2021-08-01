package com.kodilla.ecommercee.order.controller;

public class OrderNotFoundException extends Exception {

    private static final String MESSAGE = "Wrong id - does not exist!";

    public OrderNotFoundException() {
        super(MESSAGE);
    }
}
