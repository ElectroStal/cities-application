package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

public class ShowPageOfCitiesServiceTest {

    @Mock private CityRepository cityRepository;
    @Mock private CityMapper cityMapper;

    private ShowPageOfCitiesService showPageOfCitiesServiceUnderTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        showPageOfCitiesServiceUnderTest = new ShowPageOfCitiesService(
                cityRepository,
                cityMapper
        );
        ReflectionTestUtils.setField(showPageOfCitiesServiceUnderTest, "pageSize", 10);
    }

    @Test
    public void findPageOfThreeCities() {
        //given
        PageImpl<City> page =new PageImpl<>(List.of(
                new City(1, "Moscow", "photo1"),
                new City(2, "Tbilisi", "photo2"),
                new City(3, "Erevan", "photo3")
        ));
        int pageNumber = 0;
        given(cityRepository.findAll(PageRequest.of(pageNumber, 10)))
                .willReturn(page);
        page.getContent().forEach(t -> given(cityMapper.cityToCityResponse(t))
                .willReturn(new CityResponse(
                        false,
                        true,
                        t.getName(),
                        t.getPhoto(),
                        UUID.randomUUID().toString(),
                        null
                )));
        given(cityMapper.pageToCityResponse(any(Page.class), anyList())).willReturn(
                new CityResponse(true,
                        false,
                        null,
                        null,
                        UUID.randomUUID().toString(),
                        page.getContent().stream().map(t -> new CityResponse(
                        false,
                        true,
                        t.getName(),
                        t.getPhoto(),
                        UUID.randomUUID().toString(),
                        null)).toList()
                )
        );

        //when
        CityResponse actual = showPageOfCitiesServiceUnderTest.getCities(0);

        //then
        assertThat(actual.getCitiesList().size()).isEqualTo(3);
    }

    @Test
    public void findPageOfOneCity() {
        //given
        PageImpl<City> page =new PageImpl<>(List.of(
                new City(1, "Moscow", "photo1")
        ));
        int pageNumber = 0;
        given(cityRepository.findAll(PageRequest.of(pageNumber, 10)))
                .willReturn(page);
        page.getContent().forEach(t -> given(cityMapper.cityToCityResponse(t))
                .willReturn(new CityResponse(
                        false,
                        true,
                        t.getName(),
                        t.getPhoto(),
                        UUID.randomUUID().toString(),
                        null
                )));
        given(cityMapper.pageToCityResponse(any(Page.class), anyList())).willReturn(
                new CityResponse(true,
                        false,
                        null,
                        null,
                        UUID.randomUUID().toString(),
                        page.getContent().stream().map(t -> new CityResponse(
                                false,
                                true,
                                t.getName(),
                                t.getPhoto(),
                                UUID.randomUUID().toString(),
                                null)).toList()
                )
        );

        //when
        CityResponse actual = showPageOfCitiesServiceUnderTest.getCities(0);

        //then
        assertThat(actual.getCitiesList().size()).isEqualTo(1);
    }

    @Test
    public void findPageOfTenCities() {
        //given
        PageImpl<City> page =new PageImpl<>(List.of(
                new City(1, "Moscow", "photo1"),
                new City(2, "Tbilisi", "photo2"),
                new City(3, "Erevan", "photo3"),
                new City(1, "Sankt-Petersburg", "photo4"),
                new City(2, "Berlin", "photo5"),
                new City(3, "Warsaw", "photo6"),
                new City(1, "Paris", "photo7"),
                new City(2, "Milan", "photo8"),
                new City(3, "Rome", "photo9"),
                new City(1, "Istanbul", "photo10")
        ));
        int pageNumber = 0;
        given(cityRepository.findAll(PageRequest.of(pageNumber, 10)))
                .willReturn(page);
        page.getContent().forEach(t -> given(cityMapper.cityToCityResponse(t))
                .willReturn(new CityResponse(
                        false,
                        true,
                        t.getName(),
                        t.getPhoto(),
                        UUID.randomUUID().toString(),
                        null
                )));
        given(cityMapper.pageToCityResponse(any(Page.class), anyList())).willReturn(
                new CityResponse(true,
                        false,
                        null,
                        null,
                        UUID.randomUUID().toString(),
                        page.getContent().stream().map(t -> new CityResponse(
                                false,
                                true,
                                t.getName(),
                                t.getPhoto(),
                                UUID.randomUUID().toString(),
                                null)).toList()
                )
        );

        //when
        CityResponse actual = showPageOfCitiesServiceUnderTest.getCities(0);

        //then
        assertThat(actual.getCitiesList().size()).isEqualTo(10);
    }

    @Test
    public void findEmptyPage() {
        //given
        PageImpl<City> page =new PageImpl<>(new ArrayList<>());
        int pageNumber = 0;
        given(cityRepository.findAll(PageRequest.of(pageNumber, 10)))
                .willReturn(page);
        page.getContent().forEach(t -> given(cityMapper.cityToCityResponse(t))
                .willReturn(new CityResponse(
                        false,
                        true,
                        t.getName(),
                        t.getPhoto(),
                        UUID.randomUUID().toString(),
                        null
                )));
        given(cityMapper.pageToCityResponse(any(Page.class), anyList())).willReturn(
                new CityResponse(true,
                        false,
                        null,
                        null,
                        UUID.randomUUID().toString(),
                        page.getContent().stream().map(t -> new CityResponse(
                                false,
                                true,
                                t.getName(),
                                t.getPhoto(),
                                UUID.randomUUID().toString(),
                                null)).toList()
                )
        );

        //when
        CityResponse actual = showPageOfCitiesServiceUnderTest.getCities(0);

        //then
        assertThat(actual.getCitiesList().size()).isEqualTo(0);
    }
}