package com.solbeg.testtask.citiesshower.controller;

import com.solbeg.testtask.citiesshower.dto.CityRequest;
import com.solbeg.testtask.citiesshower.dto.CityResponse;
import com.solbeg.testtask.citiesshower.service.CitiesUpdateService;
import com.solbeg.testtask.citiesshower.service.FindCityByNameService;
import com.solbeg.testtask.citiesshower.service.ShowPageOfCitiesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "City", description = "City controller")
@RestController
@RequestMapping(path = "api/city")
@Slf4j
@RequiredArgsConstructor
public class CityController {

    private final ShowPageOfCitiesService showPageOfCitiesService;
    private final FindCityByNameService findCityByNameService;
    private final CitiesUpdateService citiesUpdateService;

    @GetMapping("/{page}")
    public ResponseEntity<CityResponse> pageRequest(@PathVariable(value="page") String page) {
        log.info("Got message for showPage service, page = {}", page);
        CityResponse result = showPageOfCitiesService.getCities(Integer.parseInt(page));
        log.info("Return message for showPage service, page = {}", page);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<CityResponse> cityFind(@RequestParam String city) {
        log.info("Got message for findCity service, city = {}", city);
        CityResponse result = findCityByNameService.findCityByName(city);
        log.info("Return message for findCity service, city = {}", city);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping
    public ResponseEntity<CityResponse> cityUpdate(@RequestBody CityRequest request) {
        log.info("Got message for update service");
        CityResponse result = citiesUpdateService.changeCity(request);
        log.info("Return message for update service");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
