package com.kodilla.ecommercee.group.controller;

public class GroupNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Wrong id - does not exist!";

    public GroupNotFoundException() {
        super(MESSAGE);
    }
}