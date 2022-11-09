package com.example.api_caller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenderResponse {

    private int count;
    private String gender;
    private String name;
    private double probability;
}
