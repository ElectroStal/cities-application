package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.exception.NotUniqueUpdateException;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CitiesUpdateService {

    private final CityRepository cityRepository;

    public CitiesUpdateService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional
    public CityResponse changeCity(CityRequest cityRequest) {
        CityResponse result;
        try {
            City city = CityMapper.INSTANCE.cityRequestToCity(cityRequest, cityRepository);
            log.info("Prepare to call database for cityUpdate with request: {}", cityRequest);
            cityRepository.save(city);
            log.info("Database called successfully for cityUpdate");
            result = CityMapper.INSTANCE.cityToCityResponse(city);
        } catch (DataIntegrityViolationException e) {
            log.error("Error during executing cityUpdate, there is already such name in database", e);
            throw new NotUniqueUpdateException(cityRequest.getName());
        }
        return result;
    }
}