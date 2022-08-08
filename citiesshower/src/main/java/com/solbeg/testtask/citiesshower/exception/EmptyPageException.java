package com.solbeg.testtask.citiesshower.exception;

public class EmptyPageException extends RuntimeException {

    private static final String MESSAGE = "Page number %d is empty";

    public EmptyPageException(int page) {
        super(String.format(MESSAGE, page));
    }
}
