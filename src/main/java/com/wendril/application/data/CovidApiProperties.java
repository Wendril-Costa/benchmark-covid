package com.wendril.application.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CovidApiProperties {
    @Value("${covid19.api.url}")
    private String apiUrl;

    @Value("${covid19.api.key}")
    private String apiKey;

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}
