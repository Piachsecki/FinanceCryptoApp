package com.piachsecki.financecryptoapp.domain.mapper;

import com.piachsecki.financecryptoapp.domain.ExchangeRate;
import com.piachsecki.financecryptoapp.domain.ResponseExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    @Mapping(source = "base", target = "base")
    @Mapping(source = "rates", target = "rates")
    @Mapping(source = "date", target = "date")
    ExchangeRate mapExchangeRate(ResponseExchangeRate responseExchangeRate);

}
