package com.lauracercas.moviecards.client;

import com.lauracercas.moviecards.config.MovieCardsServiceConfig;
import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Cliente REST para comunicarse con el servicio moviecards-service
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 21/02/2026
 */
@Component
public class MovieCardsServiceClient {

    private final RestTemplate restTemplate;
    private final MovieCardsServiceConfig config;

    public MovieCardsServiceClient(RestTemplate restTemplate, MovieCardsServiceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    // Métodos para Movies
    public List<Movie> getAllMovies() {
        try {
            ResponseEntity<List<Movie>> response = restTemplate.exchange(
                    config.getServiceUrl() + "/movies",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Movie>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener las películas del servicio", e);
        }
    }

    public Movie getMovieById(Integer movieId) {
        try {
            ResponseEntity<Movie> response = restTemplate.getForEntity(
                    config.getServiceUrl() + "/movies/" + movieId,
                    Movie.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener la película con ID: " + movieId, e);
        }
    }

    public Movie saveMovie(Movie movie) {
        try {
            ResponseEntity<Movie> response = restTemplate.postForEntity(
                    config.getServiceUrl() + "/movies",
                    movie,
                    Movie.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al guardar la película", e);
        }
    }

    public Movie updateMovie(Integer movieId, Movie movie) {
        try {
            restTemplate.put(
                    config.getServiceUrl() + "/movies/" + movieId,
                    movie
            );
            return getMovieById(movieId);
        } catch (RestClientException e) {
            throw new RuntimeException("Error al actualizar la película con ID: " + movieId, e);
        }
    }

    // Métodos para Actors
    public List<Actor> getAllActors() {
        try {
            ResponseEntity<List<Actor>> response = restTemplate.exchange(
                    config.getServiceUrl() + "/actors",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Actor>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener los actores del servicio", e);
        }
    }

    public Actor getActorById(Integer actorId) {
        try {
            ResponseEntity<Actor> response = restTemplate.getForEntity(
                    config.getServiceUrl() + "/actors/" + actorId,
                    Actor.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener el actor con ID: " + actorId, e);
        }
    }

    public Actor saveActor(Actor actor) {
        try {
            ResponseEntity<Actor> response = restTemplate.postForEntity(
                    config.getServiceUrl() + "/actors",
                    actor,
                    Actor.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al guardar el actor", e);
        }
    }

    // Métodos para Cards (relación Actor-Movie)
    public String registerActorInMovie(Integer movieId, Integer actorId) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    config.getServiceUrl() + "/movies/" + movieId + "/actors/" + actorId,
                    null,
                    String.class
            );
            return response.getStatusCode() == HttpStatus.OK ? "Éxito" : "Error";
        } catch (RestClientException e) {
            throw new RuntimeException("Error al registrar el actor en la película", e);
        }
    }
}
