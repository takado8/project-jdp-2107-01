package com.kodilla.ecommercee.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find product in the database")
public class ProductNotFoundException extends Exception{
    public ProductNotFoundException() {
        super("Could not find product in the database");
    }
}