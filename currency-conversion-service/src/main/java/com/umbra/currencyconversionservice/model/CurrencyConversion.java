package com.umbra.currencyconversionservice.model;

import java.math.BigDecimal;

public record CurrencyConversion(
        String id,
        String from,
        String to,
        BigDecimal conversionMultiple,
        BigDecimal quantity,
        BigDecimal totalCalculatedAmount,
        String environment
) {
}
