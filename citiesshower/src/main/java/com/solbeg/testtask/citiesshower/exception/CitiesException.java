package com.solbeg.testtask.citiesshower.exception;

public class CitiesException extends RuntimeException {

    private final String errorMessage;

    public CitiesException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
