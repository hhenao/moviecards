package com.lauracercas.moviecards.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuración para el cliente REST del servicio moviecards-service
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 21/02/2026
 */
@Configuration
public class MovieCardsServiceConfig {

    @Value("${moviecards.service.url:${MOVIECARDS_SERVICE_URL:http://localhost:8080/api}}")
    private String serviceUrl;

    @Value("${moviecards.service.timeout:5000}")
    private int timeout;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public int getTimeout() {
        return timeout;
    }
}
