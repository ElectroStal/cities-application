package com.solbeg.testtask.citiesshower.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TechEntity {
    private int errorCode;
    private String errorMessage;
}
