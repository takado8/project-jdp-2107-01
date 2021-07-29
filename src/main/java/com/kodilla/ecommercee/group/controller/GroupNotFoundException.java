package com.kodilla.ecommercee.group.controller;

import java.util.function.Supplier;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String message) {
        super(message);
    }

}
