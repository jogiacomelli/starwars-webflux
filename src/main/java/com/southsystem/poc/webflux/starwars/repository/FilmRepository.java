package com.southsystem.poc.webflux.starwars.repository;

import com.southsystem.poc.webflux.starwars.database.Film;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FilmRepository extends ReactiveMongoRepository<Film, Integer> {
    Flux<Film> findByTitleContaining(String name);
}
