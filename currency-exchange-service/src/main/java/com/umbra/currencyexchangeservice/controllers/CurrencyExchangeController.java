package com.umbra.currencyexchangeservice.controllers;


import com.umbra.currencyexchangeservice.models.dto.CurrencyExchangeDTO;
import com.umbra.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency-exchange-service")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchangeDTO getExchange(
            @PathVariable("fromCurrency") String fromCurrency,
            @PathVariable("toCurrency") String toCurrency) {
        var currencyExchange = currencyExchangeRepository.findByFromAndTo(fromCurrency, toCurrency);

        if (currencyExchange.isEmpty()){
            throw new RuntimeException("Could not find currency pair " + fromCurrency + "/" + toCurrency);
        }

        return new CurrencyExchangeDTO(1001,
                currencyExchange.get().getFrom(),
                currencyExchange.get().getTo(),
                currencyExchange.get().getConversionMultiple(),
                "Instance:" + environment.getProperty("local.server.port")
        );
    }
}
