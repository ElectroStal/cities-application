package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.exception.NoSuchResourceException;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FindCityByNameService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public FindCityByNameService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Transactional(readOnly = true)
    public CityResponse findCityByName(String cityName) {
        log.info("Prepare to call database for operation findCity for message with requestParameter = {}", cityName);
        City city = getCityByNameOrThrowException(cityName);
        log.info("Database called successfully for request with response = {}", city);
        return cityMapper.cityToCityResponse(city);
    }

    private City getCityByNameOrThrowException(String name) {
        return cityRepository.findByName(name)
                .orElseThrow(() -> new NoSuchResourceException(name));
    }
}
