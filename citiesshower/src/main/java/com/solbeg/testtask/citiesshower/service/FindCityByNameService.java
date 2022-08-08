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

    public FindCityByNameService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public CityResponse findCityByName(String cityName) {
        log.info("Prepare to call database for operation findCity for message with requestParameter = {}", cityName);
        City city = cityRepository.findByName(cityName);
        if (city == null) {
            throw new NoSuchResourceException(cityName);
        }
        log.info("Database called successfully for request with response = {}", city);
        return CityMapper.INSTANCE.cityToCityResponse(city);
    }
}
