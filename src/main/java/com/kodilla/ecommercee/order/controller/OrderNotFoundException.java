package com.kodilla.ecommercee.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find order in the database")
public class OrderNotFoundException extends Exception{
    public OrderNotFoundException() {
        super("Could not find order in the database");
    }
}
