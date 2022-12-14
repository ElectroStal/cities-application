package com.solbeg.testtask.citiesshower.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityRequest {
    @NotBlank(message = "City uuid should not be null or blank")
    private String uuid;
    @NotBlank(message = "City name should not be null or blank")
    private String name;
    @NotBlank(message = "City photo should not be null or blank")
    private String photo;
}
