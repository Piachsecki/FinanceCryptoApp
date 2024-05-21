package com.piachsecki.financecryptoapp.service;

import com.piachsecki.financecryptoapp.domain.EnumCurrency;
import com.piachsecki.financecryptoapp.domain.ExchangeRate;
import com.piachsecki.financecryptoapp.domain.ResponseExchangeRate;
import com.piachsecki.financecryptoapp.domain.mapper.ExchangeRateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {
    @Value(value = "${API_KEY_EXCHANGE_RATES}")
    private String apikey;

    @Value(value = "${API_BASE_EXCHANGE_RATES_URL}")
    private String baseUrl;

    private final ExchangeRateMapper exchangeRateMapper;


    private final RestTemplate restTemplate;


    public ExchangeRate calculateRate(EnumCurrency base, List<EnumCurrency> targets, LocalDate date) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", apikey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        final HttpEntity<String> headersEntity = new HttpEntity<>(headers);

        String symbols = String.join("%2C", targets.stream().map(EnumCurrency::name).toArray(String[]::new));


        String url = baseUrl + date + "?symbols=" + symbols + "&base=" + base;
        ResponseExchangeRate response = restTemplate.exchange(url, HttpMethod.GET, headersEntity, ResponseExchangeRate.class).getBody();
        return exchangeRateMapper.mapExchangeRate(response);

    }
}
