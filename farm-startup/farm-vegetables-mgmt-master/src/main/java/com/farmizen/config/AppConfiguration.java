package com.farmizen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farmizen.domain.entity.handlers.VegetableEventHandler;

@Configuration
public class AppConfiguration {

    @Bean
    VegetableEventHandler landEventHandler() {
      return new VegetableEventHandler();
    }
}
