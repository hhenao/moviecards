package com.lauracercas.moviecards.integrationtest.repositories;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 * Modificado: 21/02/2026 - Deshabilitado: Estas pruebas ya no aplican ya que ahora
 * se usa el servicio externo moviecards-service en lugar de repositorios JPA locales.
 * 
 * NOTA: Estas pruebas deberían ser reemplazadas por pruebas de integración que
 * prueben el cliente REST contra un servicio mock o un servicio de prueba.
 * Para pruebas de integración del servicio REST, usar MockRestServiceServer
 * o WireMock para simular el servicio externo.
 */
/*
@DataJpaTest
public class ActorJPAIT {

    @Autowired
    private ActorJPA actorJPA;

    @Test
    public void testSaveActor() {
        Actor actor = new Actor();
        actor.setName("actor");
        actor.setBirthDate(new Date());
        actor.setCountry("spain");

        Actor savedActor = actorJPA.save(actor);

        assertNotNull(savedActor.getId());

        Optional<Actor> foundActor = actorJPA.findById(savedActor.getId());

        assertTrue(foundActor.isPresent());
        assertEquals(savedActor, foundActor.get());
    }

    @Test
    public void testFindById() {
        Actor actor = new Actor();
        actor.setName("actor");
        actor.setBirthDate(new Date());
        Actor savedActor = actorJPA.save(actor);

        Optional<Actor> foundActor = actorJPA.findById(savedActor.getId());

        assertTrue(foundActor.isPresent());
        assertEquals(savedActor, foundActor.get());
    }
}
*/
