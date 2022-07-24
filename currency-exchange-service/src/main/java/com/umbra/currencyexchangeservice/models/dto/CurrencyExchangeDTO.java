package com.umbra.currencyexchangeservice.models.dto;

import java.math.BigDecimal;

public record CurrencyExchangeDTO(
        long id,
        String from,
        String to,
        BigDecimal conversionMultiple,
        String environment
        ) {
}
