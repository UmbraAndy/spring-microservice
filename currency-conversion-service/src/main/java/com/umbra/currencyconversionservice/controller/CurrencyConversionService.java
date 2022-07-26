package com.umbra.currencyconversionservice.controller;

import com.umbra.currencyconversionservice.model.CurrencyConversion;
import com.umbra.currencyconversionservice.model.CurrencyExchangeResponse;
import com.umbra.currencyconversionservice.service.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("currency-conversion-service")
public class CurrencyConversionService {

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}/quantity/{amount}")
    public CurrencyConversion getConversion(
            @PathVariable("fromCurrency") String fromCurrency,
            @PathVariable("toCurrency") String toCurrency,
            @PathVariable("amount") BigDecimal amount) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("fromCurrency", fromCurrency);
        uriVariables.put("toCurrency", toCurrency);
        var conversionExchangeResponseEntity = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/currency-exchange-service/from/{fromCurrency}/to/{toCurrency}",
                        CurrencyExchangeResponse.class,
                        uriVariables);
        var currencyExchange = conversionExchangeResponseEntity.getBody();
        var exchangeRate = Objects.requireNonNull(currencyExchange).conversionMultiple();
        return new CurrencyConversion(
                currencyExchange.id(),
                currencyExchange.from(),
                currencyExchange.to(),
                exchangeRate,
                amount,
                exchangeRate.multiply(amount),
                currencyExchange.environment()
                );
    }

    @GetMapping("feign/from/{fromCurrency}/to/{toCurrency}/quantity/{amount}")
    public CurrencyConversion getConversionUsingFeign(
            @PathVariable("fromCurrency") String fromCurrency,
            @PathVariable("toCurrency") String toCurrency,
            @PathVariable("amount") BigDecimal amount) {


        var currencyExchange = currencyExchangeServiceProxy.getExchangeRate(fromCurrency,toCurrency);
        var exchangeRate = Objects.requireNonNull(currencyExchange).conversionMultiple();
        return new CurrencyConversion(
                currencyExchange.id(),
                currencyExchange.from(),
                currencyExchange.to(),
                exchangeRate,
                amount,
                exchangeRate.multiply(amount),
                currencyExchange.environment()
        );
    }
}
