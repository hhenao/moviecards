package com.lauracercas.moviecards.service.actor;


import com.lauracercas.moviecards.client.MovieCardsServiceClient;
import com.lauracercas.moviecards.model.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 * Modificado: 21/02/2026 - Integración con moviecards-service - prueba
 */
@Service
public class ActorServiceImpl implements ActorService {

    private final MovieCardsServiceClient serviceClient;

    public ActorServiceImpl(MovieCardsServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @Override
    public List<Actor> getAllActors() {
        return serviceClient.getAllActors();
    }

    @Override
    public Actor save(Actor actor) {
        return serviceClient.saveActor(actor);
    }

    @Override
    public Actor getActorById(Integer actorId) {
        return serviceClient.getActorById(actorId);
    }
}
