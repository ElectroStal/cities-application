package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShowPageOfCitiesServiceTest {

    @Test
    public void getCitiesCheck() {
        CityRepository cityRepository = Mockito.mock(CityRepository.class);
        ShowPageOfCitiesService showPageOfCitiesService = new ShowPageOfCitiesService(cityRepository, Mappers.getMapper(CityMapper.class));
        ReflectionTestUtils.setField(showPageOfCitiesService, "pageSize", 10);
        City testCity = new City(1, "test", "test");
        testCity.setUuid(UUID.randomUUID());
        PageImpl<City> page = new PageImpl<>(List.of(testCity));
        when(cityRepository.findAll(any(Pageable.class))).thenReturn(page);
        showPageOfCitiesService.getCities(1);
        verify(cityRepository).findAll(any(Pageable.class));
    }
}