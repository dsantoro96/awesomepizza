package com.pizza.awesomepizza.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class GeneralConfiguration {

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

}
