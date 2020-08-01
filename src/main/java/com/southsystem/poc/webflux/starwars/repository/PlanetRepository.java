package com.southsystem.poc.webflux.starwars.repository;

import com.southsystem.poc.webflux.starwars.database.Planet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends ReactiveMongoRepository<Planet, String> {
}
