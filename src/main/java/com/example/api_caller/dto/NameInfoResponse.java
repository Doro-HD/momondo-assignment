package com.example.api_caller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class NameInfoResponse {

    private String name;
    private String gender;
    private double genderProbability;
    private int age;
    private int ageCount;
    private String country;
    private double countryProbability;

    public NameInfoResponse(GenderResponse genderResponse, AgeResponse ageResponse, NationalityResponse nationalityResponse) {
        this.name = genderResponse.getName();
        this.gender = genderResponse.getGender();
        this.genderProbability = genderResponse.getProbability();

        this.age = ageResponse.getAge();
        this.ageCount = ageResponse.getCount();

        Nationality nationality = nationalityResponse.getMostLikely();
        this.country = nationality.getCountry_id();
        this.countryProbability = nationality.getProbability();
    }
}
