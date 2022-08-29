package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.exception.NoSuchResourceException;
import com.solbeg.testtask.citiesshower.exception.NotUniqueUpdateException;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

public class CitiesUpdateServiceTest {

    @Mock private CityRepository cityRepository;
    @Mock private CityMapper cityMapper;

    private CitiesUpdateService citiesUpdateServiceUnderTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        citiesUpdateServiceUnderTest = new CitiesUpdateService(
                cityRepository,
                cityMapper
        );
    }

    @Test
    public void updateCityCorrect() {
        //given
        CityRequest cityRequest = new CityRequest("8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                "Batumi",
                "photoRef1");
        City existingCity = new City(1, "Baku", "photoRef");
        City newCity = new City(1, "Batumi", "photoRef1");
        given(cityRepository.findById(
                UUID.fromString("8231cd30-22b4-4ca0-b9fe-0baec165ce0f")))
                .willReturn(Optional.of(existingCity));
        given(cityMapper.cityRequestToCity(cityRequest, existingCity)).willReturn(newCity);
        given(cityMapper.cityToCityResponse(newCity)).willReturn(new CityResponse(
                true,
                false,
                newCity.getName(),
                newCity.getPhoto(),
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                null
        ));

        //when
        CityResponse actual = citiesUpdateServiceUnderTest.changeCity(cityRequest);

        //then
        assertThat(actual.getName()).isEqualTo("Batumi");
        assertThat(actual.getPhoto()).isEqualTo("photoRef1");
    }

    @Test
    public void updateCityNotFound() {
        //given
        CityRequest cityRequest = new CityRequest("8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                "Batumi",
                "photoRef1");
        City existingCity = new City(1, "Baku", "photoRef");
        City newCity = new City(1, "Batumi", "photoRef1");
        given(cityRepository.findById(
                UUID.fromString("8231cd30-22b4-4ca0-b9fe-0baec165ce0f")))
                .willReturn(Optional.empty());
        given(cityMapper.cityRequestToCity(cityRequest, existingCity)).willReturn(newCity);
        given(cityMapper.cityToCityResponse(newCity)).willReturn(new CityResponse(
                true,
                false,
                newCity.getName(),
                newCity.getPhoto(),
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                null
        ));

        //when
        assertThatThrownBy(() -> citiesUpdateServiceUnderTest.changeCity(cityRequest))

        //Then
                .isInstanceOf(NoSuchResourceException.class)
                .hasMessage("Resource with name city doesn't exist");
    }

    @Test
    public void updateCityExceptionDuringSave() {
        //given
        CityRequest cityRequest = new CityRequest("8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                "Batumi",
                "photoRef1");
        City existingCity = new City(1, "Baku", "photoRef");
        City newCity = new City(1, "Batumi", "photoRef1");
        given(cityRepository.findById(
                UUID.fromString("8231cd30-22b4-4ca0-b9fe-0baec165ce0f")))
                .willReturn(Optional.of(existingCity));
        given(cityRepository.save(newCity)).willThrow(new DataIntegrityViolationException(""));
        given(cityMapper.cityRequestToCity(cityRequest, existingCity)).willReturn(newCity);
        given(cityMapper.cityToCityResponse(newCity)).willReturn(new CityResponse(
                true,
                false,
                newCity.getName(),
                newCity.getPhoto(),
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                null
        ));

        //when
        assertThatThrownBy(() -> citiesUpdateServiceUnderTest.changeCity(cityRequest))

        //then
                .isInstanceOf(NotUniqueUpdateException.class)
                .hasMessage("Resource with unique name Batumi already exists");
    }

}