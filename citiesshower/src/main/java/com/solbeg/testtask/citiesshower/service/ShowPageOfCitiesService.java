package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.entity.City;
import com.solbeg.testtask.citiesshower.mapper.CityMapper;
import com.solbeg.testtask.citiesshower.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ShowPageOfCitiesService {

    @Value("${page.size}")
    private int pageSize;

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public ShowPageOfCitiesService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Transactional(readOnly = true)
    public CityResponse getCities(int pageNumber) {
        log.info("Prepare to call database for operation showPage for page = {}", pageNumber);
        Page<City> page = cityRepository.findAll(PageRequest.of(pageNumber, pageSize));
        log.info("Database called successfully for operation showPage for page = {}", pageNumber);
        return cityMapper.pageToCityResponse(page, getCitiesList(page));
    }

    private List<CityResponse> getCitiesList(Page<City> page) {
        return page.getContent()
                .stream()
                .map(cityMapper::cityToCityResponse)
                .toList();
    }
}
