package com.solbeg.testtask.citiesshower.mapper;

import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "id", expression = "java(cityRepository.findByName(cityRequest.getOldName()).getId())")
    City cityRequestToCity(CityRequest cityRequest, CityRepository cityRepository);

    CityResponse cityToCityResponse(City city);

    @Mapping(target = "hasNextPage", expression = "java(page.hasNext())")
    @Mapping(target = "hasPreviousPage", expression = "java(page.hasPrevious())")
    @Mapping(target = "citiesList", expression = "java(page.getContent().stream().map(CityMapper.INSTANCE::cityToCityResponse).toList())")
    CityResponse pageToCityResponse(Page<City> page);

}
