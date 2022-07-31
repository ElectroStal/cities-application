package com.solbeg.testtask.citiesshower.service;

import com.solbeg.testtask.citiesshower.model.Message;
import com.solbeg.testtask.citiesshower.model.TechEntity;
import org.springframework.stereotype.Service;

@Service
public class ErrorHandler {

    public Message createErrorMessage(int errorCode, String errorMessage, Message message) {
        TechEntity techEntity = new TechEntity();
        techEntity.setErrorCode(errorCode);
        techEntity.setErrorMessage(errorMessage);
        message.setTechEntity(techEntity);
        return message;
    }
}
