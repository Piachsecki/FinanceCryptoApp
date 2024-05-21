package com.piachsecki.financecryptoapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    private EnumCurrency base;

    private LocalDate date;

    private Map<EnumCurrency, BigDecimal> rates;
}
