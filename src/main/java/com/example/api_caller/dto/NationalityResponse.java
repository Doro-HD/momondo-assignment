package com.example.api_caller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class NationalityResponse {

    private List<Nationality> country;
    private String name;

    public Nationality getMostLikely() {
        Optional<Nationality> nationalityOptional = country.stream()
                .max(Comparator.comparingDouble(Nationality::getProbability));

        return nationalityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

@Getter
@Setter
class Nationality {

    private String country_id;
    private double probability;
}
