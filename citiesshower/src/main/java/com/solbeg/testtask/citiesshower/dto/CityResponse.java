package com.solbeg.testtask.citiesshower.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityResponse {

    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private String name;
    private String photo;
    private String uuid;
    List<CityResponse> citiesList;

}
