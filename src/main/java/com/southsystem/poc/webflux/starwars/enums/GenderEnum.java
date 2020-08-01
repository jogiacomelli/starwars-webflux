package com.southsystem.poc.webflux.starwars.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.Objects;

public enum GenderEnum {
    MALE, FEMALE, NB;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static GenderEnum getEnumFromValue(JsonNode json){
        return Arrays.stream(values())
                .filter(docType -> Objects.equals(docType.name(), json.asText()))
                .findFirst()
                .orElse(NB);
    }
}
