package com.solbeg.testtask.citiesshower.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TechEntity {
    private int errorCode;
    private String errorMessage;
}
