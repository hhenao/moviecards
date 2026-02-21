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
public class MovieJPAIT {

    @Autowired
    private MovieJPA movieJPA;

    @Test
    public void testSaveMovie() {
        Movie movie = new Movie();
        movie.setTitle("Movie");
        movie.setCountry("country");
        movie.setReleaseYear(1995);
        movie.setDuration(190);
        movie.setDirector("Director");
        movie.setGenre("Genre");
        movie.setSinopsis("sinopsis");

        Movie savedMovie = movieJPA.save(movie);

        assertNotNull(savedMovie.getId());

        Optional<Movie> foundMovie = movieJPA.findById(savedMovie.getId());

        assertTrue(foundMovie.isPresent());
        assertEquals(savedMovie, foundMovie.get());
    }

    @Test
    public void testFindById() {
        Movie movie = new Movie();
        movie.setTitle("movie2");
        Movie savedMovie = movieJPA.save(movie);

        Optional<Movie> foundMovie = movieJPA.findById(savedMovie.getId());
        assertTrue(foundMovie.isPresent());
        assertEquals(savedMovie, foundMovie.get());
    }
}
*/
