package com.lauracercas.moviecards.client.exception;

/**
 * Excepción personalizada para errores al comunicarse con el servicio moviecards-service
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 21/02/2026
 */
public class MovieCardsServiceException extends RuntimeException {

    private final String serviceUrl;
    private final Integer statusCode;
    private final String responseBody;

    public MovieCardsServiceException(String message, String serviceUrl) {
        super(message);
        this.serviceUrl = serviceUrl;
        this.statusCode = null;
        this.responseBody = null;
    }

    public MovieCardsServiceException(String message, String serviceUrl, Throwable cause) {
        super(message, cause);
        this.serviceUrl = serviceUrl;
        this.statusCode = null;
        this.responseBody = null;
    }

    public MovieCardsServiceException(String message, String serviceUrl, Integer statusCode, String responseBody) {
        super(message);
        this.serviceUrl = serviceUrl;
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public MovieCardsServiceException(String message, String serviceUrl, Integer statusCode, String responseBody, Throwable cause) {
        super(message, cause);
        this.serviceUrl = serviceUrl;
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
