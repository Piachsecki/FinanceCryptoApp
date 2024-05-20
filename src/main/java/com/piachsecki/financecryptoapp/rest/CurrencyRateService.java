package com.piachsecki.financecryptoapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {
    private final WebClient webClient;

    @Value("${API_KEY}")
    private String apiKey;

    public Mono<String> getRatesFromApi() {
        String base = "EUR";
        String symbols = "HUF, PLN, USD";
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("base", base)
                        .queryParam("symbols", symbols)
                        .build())
                .headers(httpHeaders -> {
                    httpHeaders.set("API-KEY", apiKey);
                }).retrieve().bodyToMono(String.class).flatMap(this::processResponse);
    }

    private Mono<String> processResponse(String response) {
        // Process the response here
        System.out.println("Response: " + response);
        // For example, you can transform it, log it, etc.
        return Mono.just(response); // Modify this as per your processing logic
    }

}
