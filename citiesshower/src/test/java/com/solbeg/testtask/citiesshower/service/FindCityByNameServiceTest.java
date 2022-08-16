package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindCityByNameServiceTest {

    @Test
    public void getCitiesCheck() {
        CityRepository cityRepository = Mockito.mock(CityRepository.class);
        FindCityByNameService findCityByNameService = new FindCityByNameService(cityRepository, Mappers.getMapper(CityMapper.class));
        City testCity = new City(1, "test", "test");
        testCity.setUuid(UUID.randomUUID());
        when(cityRepository.findByName(anyString())).thenReturn(Optional.of(testCity));
        findCityByNameService.findCityByName("test");
        verify(cityRepository).findByName(anyString());
    }
}