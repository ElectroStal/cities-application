package com.solbeg.testtask.citiesshower.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("security")
@Data
public class SecurityProperties {

    private String successLoginUrl;
}
