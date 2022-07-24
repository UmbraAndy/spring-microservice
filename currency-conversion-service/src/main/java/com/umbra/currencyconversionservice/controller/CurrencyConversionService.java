package com.umbra.currencyconversionservice.controller;

import com.umbra.currencyconversionservice.model.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionService {

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}/quantity/{amount}")
    public CurrencyConversion getConversion(
            @PathVariable("fromCurrency") String fromCurrency,
            @PathVariable("toCurrency") String toCurrency,
            @PathVariable("amount") BigDecimal amount){

    }
}
