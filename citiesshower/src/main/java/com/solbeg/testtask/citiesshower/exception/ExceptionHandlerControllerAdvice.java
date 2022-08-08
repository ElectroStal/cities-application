package com.solbeg.testtask.citiesshower.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(NoSuchResourceException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchResourceException(NoSuchResourceException ex) {
        return getResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NotUniqueUpdateException.class)
    public ResponseEntity<ErrorResponse> handleNotUniqueUpdateException(NotUniqueUpdateException ex) {
        return getResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EmptyPageException.class)
    public ResponseEntity<ErrorResponse> handleEmptyPageException(EmptyPageException ex) {
        return getResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        return getResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> getResponse(HttpStatus httpStatus, String error) {
        return ResponseEntity
                .status(httpStatus)
                .body(ErrorResponse.builder()
                        .error(error)
                        .status(httpStatus.value())
                        .build());
    }
}
