package com.solbeg.testtask.citiesshower.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.testtask.citiesshower.model.City;
import com.solbeg.testtask.citiesshower.model.Message;
import com.solbeg.testtask.citiesshower.repository.CitiesRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindCityByNameServiceTest {

    @Test
    public void getCitiesCheck() throws IOException {
        CitiesRepository citiesRepository = Mockito.mock(CitiesRepository.class);
        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        FindCityByNameService findCityByNameService = new FindCityByNameService(citiesRepository, errorHandler);
        InputStream resource = getClass().getClassLoader().getResourceAsStream("files/request.json");
        String request = new String(IOUtils.toByteArray(resource), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(request, Message.class);
        City city = new City(1, "test", "test");
        when(citiesRepository.findByName(anyString())).thenReturn(city);
        findCityByNameService.findCityByName(message);
        verify(citiesRepository).findByName(anyString());
    }
}