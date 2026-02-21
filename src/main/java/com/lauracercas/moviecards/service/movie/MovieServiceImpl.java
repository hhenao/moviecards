package com.lauracercas.moviecards.service.movie;


import com.lauracercas.moviecards.client.MovieCardsServiceClient;
import com.lauracercas.moviecards.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 * Modificado: 21/02/2026 - Integración con moviecards-service
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieCardsServiceClient serviceClient;

    public MovieServiceImpl(MovieCardsServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }


    @Override
    public List<Movie> getAllMovies() {
        return serviceClient.getAllMovies();
    }

    @Override
    public Movie save(Movie movie) {
        if (movie.getId() != null) {
            return serviceClient.updateMovie(movie.getId(), movie);
        } else {
            return serviceClient.saveMovie(movie);
        }
    }

    @Override
    public Movie getMovieById(Integer movieId) {
        return serviceClient.getMovieById(movieId);
    }
}
