package com.farm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.farm.domain.entity.handlers.LandEventHandler;

@Configuration
public class AppConfiguration {

    @Bean
    LandEventHandler landEventHandler() {
      return new LandEventHandler();
    }
    
    @Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
