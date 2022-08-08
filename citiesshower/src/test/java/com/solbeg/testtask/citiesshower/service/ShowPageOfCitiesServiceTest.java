package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShowPageOfCitiesServiceTest {

    @Test
    public void getCitiesCheck() {
        CityRepository cityRepository = Mockito.mock(CityRepository.class);
        ShowPageOfCitiesService showPageOfCitiesService = new ShowPageOfCitiesService(cityRepository);
        PageImpl<City> page = new PageImpl<>(List.of(new City(1, "test", "test")));
        when(cityRepository.findAll(any(Pageable.class))).thenReturn(page);
        showPageOfCitiesService.getCities(1);
        verify(cityRepository).findAll(any(Pageable.class));
    }
}