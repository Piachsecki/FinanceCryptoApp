package com.piachsecki.financecryptoapp.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseExchangeRate {

    @JsonProperty("base")
    private final EnumCurrency base = EnumCurrency.PLN;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("rates")
    private Map<EnumCurrency, BigDecimal> rates;
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("timestamp")
    private long timestamp;
}
