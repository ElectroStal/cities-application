package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.exception.NoSuchResourceException;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

public class FindCityByNameServiceTest {

    @Mock private CityRepository cityRepository;
    @Mock private CityMapper cityMapper;

    private FindCityByNameService findCityByNameServiceUnderTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        findCityByNameServiceUnderTest = new FindCityByNameService(
                cityRepository,
                cityMapper
        );
    }

    @Test
    public void findOneCityByName() {
        //given
        String cityName = "Moscow";
        City city = new City(1, "Moscow", "photoRef");
        given(cityRepository.findByName("Moscow")).willReturn(Optional.of(city));
        given(cityMapper.cityToCityResponse(city)).willReturn(new CityResponse(
                false,
                true,
                city.getName(),
                city.getPhoto(),
                UUID.randomUUID().toString(),
                null
        ));

        //when
        CityResponse actual = findCityByNameServiceUnderTest.findCityByName(cityName);

        //then
        assertThat(cityName).isEqualTo(actual.getName());
    }

    @Test
    public void findOneCityByNameMultipleTimes() {
        //given
        String cityName = "Moscow";
        City city = new City(1, "Moscow", "photoRef");
        given(cityRepository.findByName("Moscow")).willReturn(Optional.of(city));
        given(cityMapper.cityToCityResponse(city)).willReturn(new CityResponse(
                false,
                true,
                city.getName(),
                city.getPhoto(),
                UUID.randomUUID().toString(),
                null
        ));

        //when
        CityResponse actual = findCityByNameServiceUnderTest.findCityByName(cityName);
        CityResponse actualTwo = findCityByNameServiceUnderTest.findCityByName(cityName);
        CityResponse actualThree = findCityByNameServiceUnderTest.findCityByName(cityName);

        //then
        assertThat(cityName).isEqualTo(actual.getName()).isEqualTo(actualTwo.getName()).isEqualTo(actualThree.getName());
    }

    @Test
    public void throwExceptionWhenNotFindCityByName() {
        //given
        String cityName = "Moscow";
        given(cityRepository.findByName("Moscow")).willReturn(Optional.empty());

        //when
        assertThatThrownBy(() -> findCityByNameServiceUnderTest.findCityByName(cityName))

        //then
                .isInstanceOf(NoSuchResourceException.class)
                .hasMessage("Resource with name Moscow doesn't exist");
    }

    @Test
    public void findCityByNameAfterThrowingException() {
        //Given
        String cityName = "Moscow";
        String cityNameTwo = "Tbilisi";
        City city = new City(1, "Moscow", "photoRef");
        given(cityRepository.findByName("Tbilisi")).willReturn(Optional.empty());
        given(cityRepository.findByName("Moscow")).willReturn(Optional.of(city));
        given(cityMapper.cityToCityResponse(city)).willReturn(new CityResponse(
                false,
                true,
                city.getName(),
                city.getPhoto(),
                UUID.randomUUID().toString(),
                null
        ));
        //When
        assertThatThrownBy(() -> findCityByNameServiceUnderTest.findCityByName(cityNameTwo))

        //Then
                .isInstanceOf(NoSuchResourceException.class)
                .hasMessage("Resource with name Tbilisi doesn't exist");
        assertThat(cityName).isEqualTo(findCityByNameServiceUnderTest.findCityByName(cityName)
                .getName());
    }

    @Test
    public void throwExceptionAfterFindCityByName() {
        //Given
        String cityName = "Moscow";
        String cityNameTwo = "Tbilisi";
        City city = new City(1, "Moscow", "photoRef");
        given(cityRepository.findByName("Tbilisi")).willReturn(Optional.empty());
        given(cityRepository.findByName("Moscow")).willReturn(Optional.of(city));
        given(cityMapper.cityToCityResponse(city)).willReturn(new CityResponse(
                false,
                true,
                city.getName(),
                city.getPhoto(),
                UUID.randomUUID().toString(),
                null
        ));
        //When
        CityResponse actual = findCityByNameServiceUnderTest.findCityByName(cityName);

        //Then
        assertThatThrownBy(() -> findCityByNameServiceUnderTest.findCityByName(cityNameTwo))
                .isInstanceOf(NoSuchResourceException.class)
                .hasMessage("Resource with name Tbilisi doesn't exist");
        assertThat(cityName).isEqualTo(actual.getName());
    }
}