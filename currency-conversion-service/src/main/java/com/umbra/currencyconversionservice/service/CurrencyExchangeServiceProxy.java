package com.umbra.currencyconversionservice.service;

import com.umbra.currencyconversionservice.model.CurrencyExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange-service/from/{from}/to/{to}")
    public CurrencyExchangeResponse getExchangeRate(
            @PathVariable String from,
            @PathVariable String to
    );
}
