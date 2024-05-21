package com.piachsecki.financecryptoapp.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticlesController {
    private final String apiKey = "7xuI4pPbNoQr6LJ19TcnXHLkMMYVJc0PaUoiPOxg";
    private final RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<?> getArticles(
            @RequestParam List<String> industries
            ){
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", apiKey);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String url = "https://api.marketaux.com/v1/news/all" + "?industries=" + String.join(",", industries.stream().toArray(String[]::new)) + "&api_token=" + apiKey;

        final HttpEntity<String> headersEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, headersEntity, java.util.Map.class);
        JSONObject jsonObject;


        if (exchange.getStatusCode() == HttpStatus.OK) {

            jsonObject = new JSONObject(Objects.requireNonNull(exchange.getBody()));
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for(int i=0; i<jsonArray.length(); i++) {

                System.out.println(jsonArray.getJSONObject(i) + " ");

            }
        }

        return ResponseEntity.ok().body("");
    }
}
