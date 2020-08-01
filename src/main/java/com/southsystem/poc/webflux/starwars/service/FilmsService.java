package com.southsystem.poc.webflux.starwars.service;

import com.southsystem.poc.webflux.starwars.database.Film;
import com.southsystem.poc.webflux.starwars.repository.FilmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.nonNull;

@Service
public class FilmsService {

    private final FilmRepository repository;

    public FilmsService(FilmRepository repository){
        this.repository = repository;
    }

    public Flux<Film> findAll() {
        return repository.findAll();
    }

    public Mono<ResponseEntity<Film>> create(Film film) {
        return repository.save(film)
                .flatMap(created ->
                        Mono.just(new ResponseEntity<>(created, HttpStatus.CREATED))
                );
    }

    public Mono<ResponseEntity<Film>> update(int filmId, Film film) {
        return repository.findById(filmId)
                .flatMap(toUpdateFilm -> {
                    toUpdateFilm.setTitle(nonNull(film.getTitle()) ? film.getTitle() : toUpdateFilm.getTitle());
                    toUpdateFilm.setReleaseDate(nonNull(film.getReleaseDate()) ? film.getReleaseDate() : toUpdateFilm.getReleaseDate());

                    return repository.save(toUpdateFilm);
                })
                .map(updatedFilm -> new ResponseEntity<>(updatedFilm, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Flux<Film> findByTitle(String title) {
        return repository.findByTitleContaining(title);
    }

    public  Mono<ResponseEntity<Film>> findById(int id) {
        return repository.findById(id)
                .flatMap(film ->
                    Mono.just(new ResponseEntity<>(film, HttpStatus.OK))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public  Mono<ResponseEntity<Void>> deleteById(int id) {
        return repository.findById(id)
                .flatMap(film ->
                        repository.delete(film)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
