package com.example.api_caller.api;

import com.example.api_caller.dto.AgeResponse;
import com.example.api_caller.dto.GenderResponse;
import com.example.api_caller.dto.NameInfoResponse;
import com.example.api_caller.dto.NationalityResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class NameInfoController {

    @GetMapping("/name-info")
    public Mono<NameInfoResponse> getNameInfo(@RequestParam("name") String name) {
        WebClient client = WebClient.create();

        Mono<AgeResponse> ageResponseMono = client.get()
                .uri("https://api.agify.io?name=" + name)
                .retrieve()
                .bodyToMono(AgeResponse.class);

        Mono<NationalityResponse> nationalityResponseMono = client.get()
                .uri("https://api.nationalize.io?name=" + name)
                .retrieve()
                .bodyToMono(NationalityResponse.class);

        Mono<GenderResponse> genderResponseMono = client.get()
                .uri("https://api.genderize.io?name=" + name)
                .retrieve()
                .bodyToMono(GenderResponse.class);

        return Mono.zip(
                nationalityResponseMono,
                ageResponseMono,
                genderResponseMono
        ).map(t -> new NameInfoResponse(t.getT3(), t.getT2(), t.getT1()));
    }
}
