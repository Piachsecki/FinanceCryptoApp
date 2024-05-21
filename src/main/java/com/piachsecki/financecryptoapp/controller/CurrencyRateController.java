package com.piachsecki.financecryptoapp.controller;

import com.piachsecki.financecryptoapp.domain.EnumCurrency;
import com.piachsecki.financecryptoapp.domain.ExchangeRate;
import com.piachsecki.financecryptoapp.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/rates")
@RequiredArgsConstructor
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;

    @GetMapping
    public ResponseEntity<ExchangeRate> getRates(@RequestParam(name = "base",required = false) EnumCurrency base,
                                                 @RequestParam(name = "target",required = false) List<EnumCurrency> target,
                                                 @RequestParam(name = "date",required = false) @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate date) {
        return ResponseEntity.ok(currencyRateService.calculateRate(base, target,date));
    }
}
