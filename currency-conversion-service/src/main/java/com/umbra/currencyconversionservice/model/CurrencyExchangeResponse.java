package com.umbra.currencyconversionservice.model;

import java.math.BigDecimal;

public record CurrencyExchangeResponse (
        long id,
        String from,
        String to,
        BigDecimal conversionMultiple,
        String environment
) {
}