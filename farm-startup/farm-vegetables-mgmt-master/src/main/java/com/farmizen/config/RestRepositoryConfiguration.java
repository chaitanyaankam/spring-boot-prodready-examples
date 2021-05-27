package com.farmizen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.farmizen.domain.entity.Vegetable;
import com.farmizen.domain.entity.VegetableCategory;

@Configuration
public class RestRepositoryConfiguration extends RepositoryRestConfigurerAdapter {
	
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config
        .exposeIdsFor(Vegetable.class, VegetableCategory.class)
        .getCorsRegistry()
        	.addMapping("/api/**").allowedOrigins("*");
    }
    
}
