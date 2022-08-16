package com.solbeg.testtask.citiesshower.repository;

import com.solbeg.testtask.citiesshower.entity.City;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends PagingAndSortingRepository<City, UUID> {

    Optional<City> findByName(final String name);
}
