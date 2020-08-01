package com.southsystem.poc.webflux.starwars.apiclient;

public class StarWarsApiClient {

//    RestTemplate restTemplate;
//
//    public StarWarsApiClient(RestTemplateBuilder builder) {
//        this.restTemplate = builder.build();
//    }
//
//
//
//    public int getTotalFilmsByPlanet(String planetName) {
//        int numFilmes = 0;
//
//        ResponseEntity<String> response = restTemplate.getForEntity("" + planetName, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            Gson gson = new Gson();
//
//            JsonArray resultArray = gson.fromJson(response.getBody(), JsonObject.class)
//                    .get("results")
//                    .getAsJsonArray();
//
//            if(resultArray.size() == 1) {
//                numFilmes = resultArray.get(0).getAsJsonObject().get("films").getAsJsonArray().size();
//            } else {
////                throw new ResourceNotFoundException("Found in the star wars universe the planet " + planetName + " was not. Yrsssss.");
//            }
//        }
//
//        return numFilmes;
//    }
}
