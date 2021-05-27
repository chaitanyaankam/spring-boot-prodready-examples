package com.farm.projection;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.farm.domain.LandSize;
import com.farm.domain.entity.Farm;
import com.farm.domain.entity.FarmOwner;
import com.farm.domain.entity.FarmStatus;

@Projection(
		  name = "farmProjection", 
		  types = { Farm.class }) 
public interface FarmProjection {

	@Value("#{target.farmId}")
	String getFarmId();
	
	LandSize getSize();
	
	FarmOwner getFarmOwner();
	
	Set<FarmStatus> getStatus();
	
	Map<String,String> getImages();
}
