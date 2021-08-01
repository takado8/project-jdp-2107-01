package com.kodilla.ecommercee.product.controller;

public class ProductNotFoundException extends Exception {

    private static final String MESSAGE = "Wrong id - does not exist!";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
