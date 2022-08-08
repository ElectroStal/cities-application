package com.solbeg.testtask.citiesshower.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private int status;
    private String error;
}
