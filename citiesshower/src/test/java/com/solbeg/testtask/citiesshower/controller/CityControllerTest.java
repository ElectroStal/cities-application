package com.solbeg.testtask.citiesshower.controller;

import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.service.CitiesUpdateService;
import com.solbeg.testtask.citiesshower.service.FindCityByNameService;
import com.solbeg.testtask.citiesshower.service.ShowPageOfCitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CityControllerTest {

    private CityController cityController;
    private ShowPageOfCitiesService showPageOfCitiesService;
    private FindCityByNameService findCityByNameService;
    private CitiesUpdateService citiesUpdateService;

    @BeforeEach
    public void prepareControllerTest() {
        showPageOfCitiesService = Mockito.mock(ShowPageOfCitiesService.class);
        findCityByNameService = Mockito.mock(FindCityByNameService.class);
        citiesUpdateService = Mockito.mock(CitiesUpdateService.class);
        cityController = new CityController(showPageOfCitiesService, findCityByNameService, citiesUpdateService);
    }

    @Test
    public void pageRequest() {
        when(showPageOfCitiesService.getCities(anyInt())).thenReturn(new CityResponse());
        assertNotNull(cityController.pageRequest("1").getBody());
    }

    @Test
    public void cityFind() {
        when(findCityByNameService.findCityByName(anyString())).thenReturn(new CityResponse());
        assertNotNull(cityController.cityFind("test").getBody());
    }

    @Test
    public void cityUpdate() {
        when(citiesUpdateService.changeCity(any(CityRequest.class))).thenReturn(new CityResponse());
        assertNotNull(cityController.cityUpdate(new CityRequest()).getBody());
    }
}