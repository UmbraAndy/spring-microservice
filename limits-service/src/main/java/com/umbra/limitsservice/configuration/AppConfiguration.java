package com.umbra.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@org.springframework.context.annotation.Configuration("limits.service")




@ConfigurationProperties("limits.service")
public record AppConfiguration(int min, int max){

}
