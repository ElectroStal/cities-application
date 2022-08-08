package com.solbeg.testtask.citiesshower.exception;

public class NotUniqueUpdateException extends RuntimeException {

    private static final String MESSAGE = "Resource with unique name %s already exists";

    public NotUniqueUpdateException(String name) {
        super(String.format(MESSAGE, name));
    }
}
