package com.solbeg.testtask.citiesshower.controller;

import com.solbeg.testtask.citiesshower.model.Message;
import com.solbeg.testtask.citiesshower.service.CitiesUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class CitiesUpdateController {
    private final CitiesUpdateService citiesUpdateService;

    public CitiesUpdateController(CitiesUpdateService citiesUpdateService) {
        this.citiesUpdateService = citiesUpdateService;
    }

    @PostMapping(value = "/cityUpdate", consumes = "application/json")
    public Message cityUpdate(@RequestBody Message request) {
        if (request.getMessageId() == null || request.getMessageId().isEmpty()) {
            String messageId = String.valueOf(UUID.randomUUID());
            request.setMessageId(messageId);
        }
        log.info("Got message for update service, messageId = {}", request.getMessageId());
        if (log.isDebugEnabled()) {
            log.debug("Message text for update service with messageId {} is {}", request.getMessageId(), request);
        }
        Message result = citiesUpdateService.changeCity(request);
        log.info("Return message for update service, messageId = {}", request.getMessageId());
        if (log.isDebugEnabled()) {
            log.debug("Message text return for update service with messageId {} is {}", result.getMessageId(), request);
        }
        return result;
    }
}
