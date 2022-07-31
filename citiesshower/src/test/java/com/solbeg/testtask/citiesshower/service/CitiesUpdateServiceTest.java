package com.solbeg.testtask.citiesshower.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.testtask.citiesshower.model.Message;
import com.solbeg.testtask.citiesshower.repository.CitiesRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

public class CitiesUpdateServiceTest {

    @Test
    public void getCitiesCheck() throws IOException {
        CitiesRepository citiesRepository = Mockito.mock(CitiesRepository.class);
        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        CitiesUpdateService citiesUpdateService = new CitiesUpdateService(citiesRepository, errorHandler);
        InputStream resource = getClass().getClassLoader().getResourceAsStream("files/request.json");
        String request = new String(IOUtils.toByteArray(resource), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(request, Message.class);
        citiesUpdateService.changeCity(message);
        verify(citiesRepository).setCity(anyInt(), anyString(), anyString());
    }
}