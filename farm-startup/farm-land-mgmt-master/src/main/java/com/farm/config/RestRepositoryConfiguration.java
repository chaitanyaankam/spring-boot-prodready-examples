package com.farm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.farm.domain.entity.Farm;
import com.farm.domain.entity.Farmer;
import com.farm.domain.entity.Land;
import com.farm.domain.entity.LandOwner;
import com.farm.projection.FarmProjection;
import com.farm.projection.LandProjection;
import com.farm.validators.LandValidators;

@Configuration
public class RestRepositoryConfiguration extends RepositoryRestConfigurerAdapter {
	
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config
        .exposeIdsFor(LandOwner.class, Land.class, Farm.class, Farmer.class)
        .getProjectionConfiguration()
        	.addProjection(LandProjection.class)
        	.addProjection(FarmProjection.class);
        config.getCorsRegistry()
        	.addMapping("/api/**").allowedOrigins("*");
    }
    
    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
        v.addValidator("beforeCreate", new LandValidators());
    }
}
