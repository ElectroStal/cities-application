package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.exception.EmptyPageException;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ShowPageOfCitiesService {

    private static final int PAGE_SIZE = 10;

    private final CityRepository cityRepository;

    public ShowPageOfCitiesService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public CityResponse getCities(int pageNumber) {
        log.info("Prepare to call database for operation showPage for page = {}", pageNumber);
        Page<City> page = cityRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
        if (page.getContent().isEmpty()) {
            throw new EmptyPageException(pageNumber);
        }
        log.info("Database called successfully for operation showPage for page = {}", pageNumber);
        return CityMapper.INSTANCE.pageToCityResponse(page);
    }
}
