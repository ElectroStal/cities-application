package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.exception.CitiesException;
import com.solbeg.testtask.citiesshower.model.BusinessEntity;
import com.solbeg.testtask.citiesshower.model.City;
import com.solbeg.testtask.citiesshower.model.Message;
import com.solbeg.testtask.citiesshower.repository.CitiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class FindCityByNameService {

    private final CitiesRepository citiesRepository;
    private final ErrorHandler errorHandler;

    public FindCityByNameService(CitiesRepository citiesRepository, ErrorHandler errorHandler) {
        this.citiesRepository = citiesRepository;
        this.errorHandler = errorHandler;
    }

    @Transactional(readOnly = true)
    public Message findCityByName(Message message) {
        Message result = new Message();
        try {
            String cityName = Optional.of(message.getBusinessEntity())
                    .map(BusinessEntity::getEntities)
                    .map(t -> t.get(0))
                    .map(City::getName)
                    .orElseThrow(() -> new CitiesException("Incorrect request"));
            log.info("Prepare to call database for operation findCity for message with messageId = {}, requestParameter = {}", message.getMessageId(), cityName);
            City city = citiesRepository.findByName(cityName);
            log.info("Database called successfully for request with messageId = {}, response = {}", message.getMessageId(), city);
            result = createMessage(city, message.getMessageId());
            result = errorHandler.createErrorMessage(0, null, result);
        } catch (Exception e) {
            log.error("Error during executing message with messageId {}", message.getMessageId(), e);
            result = errorHandler.createErrorMessage(1, "Internal error", result);
        }
        return result;
    }

    private Message createMessage(City city, String messageId) {
        BusinessEntity businessEntity = BusinessEntity
                .builder()
                .entities(new ArrayList<>())
                .build();
        businessEntity.getEntities().add(city);
        return Message.builder()
                .messageId(messageId)
                .businessEntity(businessEntity)
                .build();
    }
}
