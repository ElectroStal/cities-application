package com.solbeg.testtask.citiesshower.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CitiesUpdateServiceTest {

    @Test
    public void getCitiesCheck() throws IOException {
        CityRepository cityRepository = Mockito.mock(CityRepository.class);
        CitiesUpdateService citiesUpdateService = new CitiesUpdateService(cityRepository);
        InputStream resource = getClass().getClassLoader().getResourceAsStream("files/request.json");
        String request = new String(IOUtils.toByteArray(resource), StandardCharsets.UTF_8);
        when(cityRepository.findByName(anyString())).thenReturn(new City(1, "test", "test"));
        ObjectMapper objectMapper = new ObjectMapper();
        CityRequest cityRequest = objectMapper.readValue(request, CityRequest.class);
        citiesUpdateService.changeCity(cityRequest);
        verify(cityRepository).save(any(City.class));
    }
}