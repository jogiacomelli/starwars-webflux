package com.southsystem.poc.webflux.starwars.controller;

import com.southsystem.poc.webflux.starwars.database.Film;
import com.southsystem.poc.webflux.starwars.service.FilmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/films")
public class FilmController {

    private FilmsService filmsService;

    public FilmController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @PostMapping
    public Mono<ResponseEntity<Film>> create(@RequestBody Film film) {
        return filmsService.create(film);
    }

    @GetMapping
    public Flux<Film> findAll() {
        return filmsService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Film>> findById(@PathVariable Integer id) {
        return filmsService.findById(id);
    }

    @GetMapping("/search")
    public Flux<Film> findByTitle(@RequestParam String title) {
        return filmsService.findByTitle(title);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Film>> update(@PathVariable Integer id, @RequestBody Film film) {
        return filmsService.update(id, film);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable int id) {
        return filmsService.deleteById(id);
    }
}
