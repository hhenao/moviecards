package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Controller
public class MovieController {

    private static final String ATTRIBUTE_MOVIE = "movie";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String VIEW_MOVIES_FORM = "movies/form";

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException ex, Model model) throws BindException {
        if (!ATTRIBUTE_MOVIE.equals(ex.getObjectName())) {
            throw ex;
        }
        model.addAllAttributes(ex.getModel());
        model.addAttribute(ATTRIBUTE_TITLE, ex.getTarget() instanceof Movie
                && ((Movie) ex.getTarget()).getId() != null
                ? Messages.EDIT_MOVIE_TITLE
                : Messages.NEW_MOVIE_TITLE);
        return VIEW_MOVIES_FORM;
    }

    @GetMapping("movies")
    public String getMoviesList(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies/list";
    }

    @GetMapping("movies/new")
    public String newMovie(Model model) {
        model.addAttribute(ATTRIBUTE_MOVIE, new Movie());
        model.addAttribute(ATTRIBUTE_TITLE, Messages.NEW_MOVIE_TITLE);
        return VIEW_MOVIES_FORM;
    }

    @PostMapping("saveMovie")
    @SuppressWarnings("java:S4684") // Las entidades se usan como DTOs ya que no hay persistencia JPA real
    public String saveMovie(@ModelAttribute(ATTRIBUTE_MOVIE) Movie movie, BindingResult result, Model model) {
        String view = VIEW_MOVIES_FORM;
        if (!result.hasErrors()) {
            Movie movieSaved = movieService.save(movie);
            Movie movieToShow = (movieSaved != null) ? movieSaved : movie;
            if (movie.getId() != null) {
                model.addAttribute("message", Messages.UPDATED_MOVIE_SUCCESS);
            } else {
                model.addAttribute("message", Messages.SAVED_MOVIE_SUCCESS);
            }
            model.addAttribute(ATTRIBUTE_MOVIE, movieToShow);
            model.addAttribute("actors", movieToShow.getActors() != null ? movieToShow.getActors() : List.of());
            model.addAttribute(ATTRIBUTE_TITLE, Messages.EDIT_MOVIE_TITLE);
        } else {
            model.addAttribute(ATTRIBUTE_MOVIE, movie);
            model.addAttribute(ATTRIBUTE_TITLE, movie.getId() != null ? Messages.EDIT_MOVIE_TITLE : Messages.NEW_MOVIE_TITLE);
        }
        return view;
    }

    @GetMapping("editMovie/{movieId}")
    public String editMovie(@PathVariable Integer movieId, Model model) {
        Movie movie = movieService.getMovieById(movieId);
        List<Actor> actors = movie.getActors();
        model.addAttribute(ATTRIBUTE_MOVIE, movie);
        model.addAttribute("actors", actors);
        model.addAttribute(ATTRIBUTE_TITLE, Messages.EDIT_MOVIE_TITLE);
        return VIEW_MOVIES_FORM;
    }


}
