package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindCityByNameServiceTest {

    @Test
    public void getCitiesCheck() {
        CityRepository cityRepository = Mockito.mock(CityRepository.class);
        FindCityByNameService findCityByNameService = new FindCityByNameService(cityRepository);
        when(cityRepository.findByName(anyString())).thenReturn(new City(1, "test", "test"));
        findCityByNameService.findCityByName("test");
        verify(cityRepository).findByName(anyString());
    }
}