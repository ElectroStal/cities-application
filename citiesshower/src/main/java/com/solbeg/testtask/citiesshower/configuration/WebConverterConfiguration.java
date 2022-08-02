package com.solbeg.testtask.citiesshower.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class WebConverterConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
            ObjectMapper objectMapper
    ) {
        MappingJackson2HttpMessageConverter bean = new MappingJackson2HttpMessageConverter();
        bean.setObjectMapper(objectMapper);
        return bean;
    }
}
