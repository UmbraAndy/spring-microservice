package com.umbra.currencyexchangeservice.repository;

import com.umbra.currencyexchangeservice.models.data.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {
    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
