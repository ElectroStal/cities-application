package com.solbeg.testtask.citiesshower.controller;

import com.solbeg.testtask.citiesshower.model.Message;
import com.solbeg.testtask.citiesshower.service.FindCityByNameService;
import com.solbeg.testtask.citiesshower.service.ShowPageOfCitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class CitiesShowerController {

    private final ShowPageOfCitiesService showPageOfCitiesService;
    private final FindCityByNameService findCityByNameService;

    public CitiesShowerController(ShowPageOfCitiesService showPageOfCitiesService, FindCityByNameService findCityByNameService) {
        this.showPageOfCitiesService = showPageOfCitiesService;
        this.findCityByNameService = findCityByNameService;
    }

    @PostMapping(value = "/pageRequest", consumes = "application/json")
    public ResponseEntity<Message> pageRequest(@RequestBody Message request) {
        getMessageId(request);
        log.info("Got message for showPage service, messageId = {}", request.getMessageId());
        if (log.isDebugEnabled()) {
            log.debug("Message text for showPage service with messageId {} is {}", request.getMessageId(), request);
        }
        Message result = showPageOfCitiesService.getCities(request);
        log.info("Return message for showPage service, messageId = {}", request.getMessageId());
        if (log.isDebugEnabled()) {
            log.debug("Message text return for showPage service with messageId {} is {}", result.getMessageId(), request);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/cityFind", consumes = "application/json")
    public ResponseEntity<Message> cityFind(@RequestBody Message request) {
        getMessageId(request);
        log.info("Got message for findCity service, messageId = {}", request.getMessageId());
        if (log.isDebugEnabled()) {
            log.debug("Message text for findCity service with messageId {} is {}", request.getMessageId(), request);
        }
        Message result = findCityByNameService.findCityByName(request);
        log.info("Return message for findCity service, messageId = {}", request.getMessageId());
        if (log.isDebugEnabled()) {
            log.debug("Message text return for findCity service with messageId {} is {}", result.getMessageId(), request);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    private void getMessageId(Message request) {
        if (request.getMessageId() == null || request.getMessageId().isEmpty()) {
            String messageId = String.valueOf(UUID.randomUUID());
            request.setMessageId(messageId);
        }
    }
}
