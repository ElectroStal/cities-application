package com.solbeg.testtask.citiesshower.mapper;

import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {

    @Mapping(target = "uuid", expression = "java(existingCity.getUuid())")
    @Mapping(target = "id", expression = "java(existingCity.getId())")
    @Mapping(target = "name", expression = "java(cityRequest.getName())")
    @Mapping(target = "photo", expression = "java(cityRequest.getPhoto())")
    City cityRequestToCity(CityRequest cityRequest, City existingCity);

    @Mapping(target = "uuid", expression = "java(city.getUuid().toString())")
    CityResponse cityToCityResponse(City city);

    @Mapping(target = "hasNextPage", expression = "java(page.hasNext())")
    @Mapping(target = "hasPreviousPage", expression = "java(page.hasPrevious())")
    @Mapping(target = "citiesList", source = "cityResponseList")
    CityResponse pageToCityResponse(Page<City> page, List<CityResponse> cityResponseList);

}
