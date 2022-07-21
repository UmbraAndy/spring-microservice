package com.umbra.limitsservice.controllers;


import com.umbra.limitsservice.configuration.AppConfiguration;
import com.umbra.limitsservice.models.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private AppConfiguration configuration;

    @GetMapping("/limits")
    public Limits getLimits() {
        return new Limits(1, configuration.min(), configuration.max());
    }
}
