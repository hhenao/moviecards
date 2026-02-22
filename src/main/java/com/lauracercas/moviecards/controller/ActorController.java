package com.lauracercas.moviecards.controller;

import com.lauracercas.moviecards.model.Actor;
import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.service.actor.ActorService;
import com.lauracercas.moviecards.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Controller
public class ActorController {

    // Constantes para nombres de atributos y vistas
    private static final String ATTRIBUTE_ACTOR = "actor";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String VIEW_ACTORS_FORM = "actors/form";

    private final ActorService actorService;

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @InitBinder(ATTRIBUTE_ACTOR)
    public void initBinder(WebDataBinder binder) {
        PropertyEditorSupport dateEditor = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                    return;
                }
                try {
                    setValue(new SimpleDateFormat(DATE_PATTERN).parse(text.trim()));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                Date value = (Date) getValue();
                return value != null ? new SimpleDateFormat(DATE_PATTERN).format(value) : "";
            }
        };
        binder.registerCustomEditor(Date.class, "deadDate", dateEditor);
        binder.registerCustomEditor(Date.class, "birthDate", dateEditor);
    }

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException ex, Model model) throws BindException {
        if (!ATTRIBUTE_ACTOR.equals(ex.getObjectName())) {
            throw ex;
        }
        model.addAllAttributes(ex.getModel());
        model.addAttribute(ATTRIBUTE_TITLE, ex.getTarget() instanceof Actor
                && ((Actor) ex.getTarget()).getId() != null
                ? Messages.EDIT_ACTOR_TITLE
                : Messages.NEW_ACTOR_TITLE);
        return VIEW_ACTORS_FORM;
    }

    @GetMapping("actors")
    public String getActorsList(Model model) {
        model.addAttribute("actors", actorService.getAllActors());
        return "actors/list";
    }

    @GetMapping("actors/new")
    public String newActor(Model model) {
        model.addAttribute(ATTRIBUTE_ACTOR, new Actor());
        model.addAttribute(ATTRIBUTE_TITLE, Messages.NEW_ACTOR_TITLE);
        return VIEW_ACTORS_FORM;
    }

    @PostMapping("saveActor")
    @SuppressWarnings("java:S4684") // Las entidades se usan como DTOs ya que no hay persistencia JPA real
    public String saveActor(@ModelAttribute(ATTRIBUTE_ACTOR) Actor actor, BindingResult result, Model model) {
        String view = VIEW_ACTORS_FORM;
        if (!result.hasErrors()) {
            Actor actorSaved = actorService.save(actor);
            if (actor.getId() != null) {
                model.addAttribute("message", Messages.UPDATED_ACTOR_SUCCESS);
            } else {
                model.addAttribute("message", Messages.SAVED_ACTOR_SUCCESS);
            }
            model.addAttribute(ATTRIBUTE_ACTOR, actorSaved);
            model.addAttribute("movies", actorSaved.getMovies() != null ? actorSaved.getMovies() : List.of());
            model.addAttribute(ATTRIBUTE_TITLE, Messages.EDIT_ACTOR_TITLE);
        } else {
            model.addAttribute(ATTRIBUTE_ACTOR, actor);
            model.addAttribute(ATTRIBUTE_TITLE, actor.getId() != null ? Messages.EDIT_ACTOR_TITLE : Messages.NEW_ACTOR_TITLE);
        }
        return view;
    }

    @GetMapping("editActor/{actorId}")
    public String editActor(@PathVariable Integer actorId, Model model) {
        Actor actor = actorService.getActorById(actorId);
        List<Movie> movies = actor.getMovies();
        model.addAttribute(ATTRIBUTE_ACTOR, actor);
        model.addAttribute("movies", movies);

        model.addAttribute(ATTRIBUTE_TITLE, Messages.EDIT_ACTOR_TITLE);

        return VIEW_ACTORS_FORM;
    }


}
