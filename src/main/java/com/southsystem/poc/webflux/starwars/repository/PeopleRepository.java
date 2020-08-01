package com.southsystem.poc.webflux.starwars.repository;

import com.southsystem.poc.webflux.starwars.database.People;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<People, Long> {
}
