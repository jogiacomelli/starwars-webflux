package com.southsystem.poc.webflux.starwars.database;

import com.southsystem.poc.webflux.starwars.enums.GenderEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "peoples")
public class People {

    @Id
    private Long id;
    private String name;
    private String birthYear;
    private GenderEnum gender;
    private Planet homeWorld;
    private String specie;

    public People(Long id, String name, String birthYear, GenderEnum gender, Planet homeWorld, String specie) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.homeWorld = homeWorld;
        this.specie = specie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Planet getHomeWorld() {
        return homeWorld;
    }

    public void setHomeWorld(Planet homeWorld) {
        this.homeWorld = homeWorld;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }
}
