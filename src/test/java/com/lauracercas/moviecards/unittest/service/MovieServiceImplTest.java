package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.client.MovieCardsServiceClient;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.movie.MovieServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 * Modificado: 21/02/2026 - Adaptado para usar moviecards-service
 */
class MovieServiceImplTest {
    @Mock
    private MovieCardsServiceClient serviceClient;
    private MovieServiceImpl sut;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = openMocks(this);
        sut = new MovieServiceImpl(serviceClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGetAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());

        when(serviceClient.getAllMovies()).thenReturn(movies);

        List<Movie> result = sut.getAllMovies();

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMovieById() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Sample Movie");

        when(serviceClient.getMovieById(anyInt())).thenReturn(movie);

        Movie result = sut.getMovieById(1);

        assertEquals(1, result.getId());
        assertEquals("Sample Movie", result.getTitle());
    }

    @Test
    public void shouldSaveMovie() {
        Movie movie = new Movie();
        movie.setTitle("New Movie");

        when(serviceClient.saveMovie(any(Movie.class))).thenReturn(movie);

        Movie result = sut.save(movie);

        assertEquals("New Movie", result.getTitle());
    }

    @Test
    public void shouldUpdateMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Updated Movie");

        when(serviceClient.updateMovie(anyInt(), any(Movie.class))).thenReturn(movie);
        when(serviceClient.getMovieById(anyInt())).thenReturn(movie);

        Movie result = sut.save(movie);

        assertEquals("Updated Movie", result.getTitle());
    }


}