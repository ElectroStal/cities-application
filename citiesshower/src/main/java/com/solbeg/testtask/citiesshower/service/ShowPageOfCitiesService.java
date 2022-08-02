package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.exception.CitiesException;
import com.solbeg.testtask.citiesshower.model.BusinessEntity;
import com.solbeg.testtask.citiesshower.model.City;
import com.solbeg.testtask.citiesshower.model.Message;
import com.solbeg.testtask.citiesshower.repository.CitiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class ShowPageOfCitiesService {

    private final CitiesRepository citiesRepository;
    private final ErrorHandler errorHandler;

    public ShowPageOfCitiesService(CitiesRepository citiesRepository, ErrorHandler errorHandler) {
        this.citiesRepository = citiesRepository;
        this.errorHandler = errorHandler;
    }

    @Transactional(readOnly = true)
    public Message getCities(Message message) {
        Message result = new Message();
        try {
            BusinessEntity businessEntity = Optional.of(message.getBusinessEntity())
                    .orElseThrow(() -> new CitiesException("Incorrect request"));
            Page<City> page = citiesRepository.findAll(PageRequest.of(businessEntity.getCurrentPage(),
                    businessEntity.getPageSize()));
            result = createMessage(page, message.getMessageId());
            log.info("Database called successfully for request with messageId = {}, response = {}", message.getMessageId(), page.getContent());
            result = errorHandler.createErrorMessage(0, null, result);
        } catch (CitiesException e) {
            result = errorHandler.createErrorMessage(1, e.getErrorMessage(), result);
        }catch (Exception e) {
            log.error("Error during executing message with messageId {}", message.getMessageId(), e);
            result = errorHandler.createErrorMessage(1, "Internal error", result);
        }
        return result;
    }

    private Message createMessage(Page<City> page, String messageId) {
        BusinessEntity businessEntity = BusinessEntity
                .builder()
                .hasNextPage(page.hasNext())
                .hasPreviousPage(page.hasPrevious())
                .entities(new ArrayList<>())
                .build();
        businessEntity.getEntities().addAll(page.getContent());
        return Message
                .builder()
                .messageId(messageId)
                .businessEntity(businessEntity)
                .build();
    }
}
