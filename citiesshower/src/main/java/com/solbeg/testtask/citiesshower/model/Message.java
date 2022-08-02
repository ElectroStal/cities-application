package com.solbeg.testtask.citiesshower.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Message {
    private String messageId;
    private TechEntity techEntity;
    private BusinessEntity businessEntity;
}
