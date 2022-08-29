package com.solbeg.testtask.citiesshower.controller;

import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.exception.EmptyPageException;
import com.solbeg.testtask.citiesshower.exception.NoSuchResourceException;
import com.solbeg.testtask.citiesshower.exception.NotUniqueUpdateException;
import com.solbeg.testtask.citiesshower.service.CitiesUpdateService;
import com.solbeg.testtask.citiesshower.service.FindCityByNameService;
import com.solbeg.testtask.citiesshower.service.ShowPageOfCitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

public class CityControllerTest {

    private CityController cityControllerUnderTest;
    @Mock private ShowPageOfCitiesService showPageOfCitiesService;
    @Mock private FindCityByNameService findCityByNameService;
    @Mock private CitiesUpdateService citiesUpdateService;

    @BeforeEach
    public void prepareControllerTest() {
        MockitoAnnotations.openMocks(this);
        cityControllerUnderTest = new CityController(
                showPageOfCitiesService,
                findCityByNameService,
                citiesUpdateService
        );
    }

    @Test
    public void pageRequestCorrect() {
        //given
        given(showPageOfCitiesService.getCities(0)).willReturn(new CityResponse(
                false,
                true,
                null,
                null,
                UUID.randomUUID().toString(),
                List.of(new CityResponse(
                        false,
                        true,
                        "Moscow",
                        "photoRef1",
                        UUID.randomUUID().toString(),
                        null
                ))
        ));

        //when
        ResponseEntity<CityResponse> responseEntity = cityControllerUnderTest.pageRequest("0");

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void pageRequestCorrectMultipleTimes() {
        //given
        given(showPageOfCitiesService.getCities(0)).willReturn(new CityResponse(
                false,
                true,
                null,
                null,
                UUID.randomUUID().toString(),
                List.of(new CityResponse(
                        false,
                        true,
                        "Moscow",
                        "photoRef1",
                        UUID.randomUUID().toString(),
                        null
                ))
        ));

        //when
        ResponseEntity<CityResponse> responseEntity = cityControllerUnderTest.pageRequest("0");
        ResponseEntity<CityResponse> responseEntity1 = cityControllerUnderTest.pageRequest("1");
        ResponseEntity<CityResponse> responseEntity2 = cityControllerUnderTest.pageRequest("2");

        //then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(responseEntity1.getStatusCode())
                .isEqualTo(responseEntity2.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void pageRequestThrowEmptyPageException() {
        //given
        given(showPageOfCitiesService.getCities(0)).willThrow(new EmptyPageException(0));

        //when
        assertThatThrownBy(() -> cityControllerUnderTest.pageRequest("0"))

        //then
                .isInstanceOf(EmptyPageException.class)
                .hasMessage("Page number 0 is empty");
    }

    @Test
    public void cityFindCorrect() {
        //given
        given(findCityByNameService.findCityByName("Moscow")).willReturn(new CityResponse(
                false,
                true,
                "Moscow",
                "photoRef1",
                UUID.randomUUID().toString(),
                null));

        //when
        ResponseEntity<CityResponse> responseEntity = cityControllerUnderTest.cityFind("Moscow");

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void cityFindCorrectMultipleTimes() {
        //given
        given(findCityByNameService.findCityByName("Moscow")).willReturn(new CityResponse(
                false,
                true,
                "Moscow",
                "photoRef1",
                UUID.randomUUID().toString(),
                null));

        //when
        ResponseEntity<CityResponse> responseEntity = cityControllerUnderTest.cityFind("Moscow");
        ResponseEntity<CityResponse> responseEntity1 = cityControllerUnderTest.cityFind("Moscow");
        ResponseEntity<CityResponse> responseEntity2 = cityControllerUnderTest.cityFind("Moscow");

        //then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(responseEntity1.getStatusCode())
                .isEqualTo(responseEntity2.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void cityFindThrowNoSuchResourceException() {
        //given
        given(findCityByNameService.findCityByName("Moscow")).willThrow(new NoSuchResourceException("Moscow"));

        //when
        assertThatThrownBy(() -> cityControllerUnderTest.cityFind("Moscow"))

                //then
                .isInstanceOf(NoSuchResourceException.class)
                .hasMessage("Resource with name Moscow doesn't exist");
    }

    @Test
    public void cityUpdateCorrect() {
        //given
        CityRequest cityRequest = new CityRequest(
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                "Moscow",
                "photoRef1"
        );
        given(citiesUpdateService.changeCity(cityRequest)).willReturn(new CityResponse(
                false,
                true,
                "Moscow",
                "photoRef1",
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                null));

        //when
        ResponseEntity<CityResponse> responseEntity = cityControllerUnderTest.cityUpdate(cityRequest);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void cityUpdateCorrectMultipleTimes() {
        CityRequest cityRequest = new CityRequest(
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                "Moscow",
                "photoRef1"
        );
        given(citiesUpdateService.changeCity(cityRequest)).willReturn(new CityResponse(
                false,
                true,
                "Moscow",
                "photoRef1",
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                null));

        //when
        ResponseEntity<CityResponse> responseEntity = cityControllerUnderTest.cityUpdate(cityRequest);
        ResponseEntity<CityResponse> responseEntity1 = cityControllerUnderTest.cityUpdate(cityRequest);
        ResponseEntity<CityResponse> responseEntity2 = cityControllerUnderTest.cityUpdate(cityRequest);

        //then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(responseEntity1.getStatusCode())
                .isEqualTo(responseEntity2.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void cityUpdateThrowNoSuchResourceException() {
        //given
        CityRequest cityRequest = new CityRequest(
                "8231cd30-22b4-4ca0-b9fe-0baec165ce0f",
                "Moscow",
                "photoRef1"
        );
        given(citiesUpdateService.changeCity(cityRequest)).willThrow(new NotUniqueUpdateException("Moscow"));

        //when
        assertThatThrownBy(() -> cityControllerUnderTest.cityUpdate(cityRequest))

                //then
                .isInstanceOf(NotUniqueUpdateException.class)
                .hasMessage("Resource with unique name Moscow already exists");
    }
}