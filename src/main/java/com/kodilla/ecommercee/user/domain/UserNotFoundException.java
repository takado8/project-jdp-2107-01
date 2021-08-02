package com.kodilla.ecommercee.user.domain;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor
public class UserNotFoundException extends Exception {

    private String message;

}
