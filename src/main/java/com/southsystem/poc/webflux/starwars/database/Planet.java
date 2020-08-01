package com.southsystem.poc.webflux.starwars.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planets")
public class Planet {

    @Id
    private String name;
    private String climate;
    private Long population;
    private String terrain;

}
