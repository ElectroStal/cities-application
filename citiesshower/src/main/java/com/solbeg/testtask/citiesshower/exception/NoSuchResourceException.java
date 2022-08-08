package com.solbeg.testtask.citiesshower.exception;

public class NoSuchResourceException extends RuntimeException {

    private static final String MESSAGE = "Resource with name %s doesn't exist";

    public NoSuchResourceException(String name) {
        super(String.format(MESSAGE, name));
    }
}
