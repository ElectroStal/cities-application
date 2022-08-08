package com.solbeg.testtask.citiesshower.repository;

import com.solbeg.testtask.citiesshower.entity.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    City findByName(String name);
}
