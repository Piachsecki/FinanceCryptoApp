package com.piachsecki.financecryptoapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piachsecki.financecryptoapp.domain.CryptoCurrency;
import com.piachsecki.financecryptoapp.service.CryptoCurrenciesService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
public class CryptoPriceController {
    private final CryptoCurrenciesService cryptoCurrenciesService;


    @GetMapping("/latest")
    public ResponseEntity<HttpStatus> getLatestPrices() {
        cryptoCurrenciesService.processRequests();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
