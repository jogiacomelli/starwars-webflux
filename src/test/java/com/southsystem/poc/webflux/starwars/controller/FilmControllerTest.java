package com.southsystem.poc.webflux.starwars.controller;

import com.southsystem.poc.webflux.starwars.database.Film;
import com.southsystem.poc.webflux.starwars.repository.FilmRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureWebTestClient
public class FilmControllerTest {

    @Autowired
    FilmRepository repository;

    @Autowired
    WebTestClient webTestClient;

    List<Film> data() throws ParseException {

        return Arrays.asList(
          new Film("A New Hope", 4, Date.valueOf("1977-05-25")),
          new Film("The Empire Strikes Back", 5, Date.valueOf("1980-05-17")),
          new Film("Return of the Jedi", 6, Date.valueOf("1983-05-25")),
          new Film("The Phantom Menace", 1, Date.valueOf("1999-05-19")),
          new Film("Attack of the Clones", 2, Date.valueOf("2002-05-16"))
        );
    }

    @BeforeEach
    void setup() throws ParseException {

        repository.deleteAll()
                .thenMany(Flux.fromIterable(data()))
                .flatMap(repository::save)
                .doOnNext(System.out::println)
                .blockLast();
    }

    @Test
    void addFilm() {

        Film film = new Film("Revenge of the Sith", 3, Date.valueOf("2005-05-19"));

        webTestClient.post().uri("/films")
                .body(Mono.just(film), Film.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Revenge of the Sith");
    }


    @Test
    void addFilm_BlankField() {

        Film film = new Film("", 7, Date.valueOf("2005-05-19"));

        webTestClient.post().uri("/films")
                .body(Mono.just(film), Film.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void addFilm_Duplicated() {

        Film film = new Film("A New Hope", 4, Date.valueOf("1977-05-25"));

        webTestClient.post().uri("/films")
                .body(Mono.just(film), Film.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void getAll() {

        webTestClient.get().uri("/films")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Film.class)
                .hasSize(5);
    }

    @Test
    void getById() {

        webTestClient.get().uri("/films/{id}", 2)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Attack of the Clones");
    }

    @Test
    void getById_NotFound() {

        webTestClient.get().uri("/films/{id}", "96")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void getByTitle() {

        Film expected = new Film("Return of the Jedi", 6, Date.valueOf("1983-05-25"));

        webTestClient.get().uri("/films/search?title={name}", "Jedi")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Film.class)
                .contains(expected);
    }

    @Test
    void getByTitle_NotFound() {

        webTestClient.get().uri("/films/search?title={name}", "Back to the Future")
                .exchange()
                .expectBodyList(Film.class)
                .hasSize(0);
    }

    @Test
    void updateFilm() {

        Film toUpdate = new Film("A New Hope - Best Film Ever", 4, Date.valueOf("1977-05-25"));

        webTestClient.put().uri("/films/{id}", 4)
                .body(Mono.just(toUpdate), Film.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Film.class)
                .consumeWith(response -> {
                    Assertions.assertEquals(toUpdate, response.getResponseBody());
                });
    }

    @Test
    void updateFilm_NotFound() {

        Film toUpdate = new Film("A New Hope - Best Film Ever", 57, Date.valueOf("1977-05-25"));

        webTestClient.put().uri("/films/{id}", 4)
                .body(Mono.just(toUpdate), Film.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void deleteFilm() {

        webTestClient.delete().uri("/films/{id}", "2")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void deleteFilm_NotFound() {

        webTestClient.delete().uri("/films/{id}", "96")
                .exchange()
                .expectStatus().isNotFound();
    }

}
