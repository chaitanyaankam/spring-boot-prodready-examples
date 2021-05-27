package com.farm.projection;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.farm.domain.Address;
import com.farm.domain.LandSize;
import com.farm.domain.entity.Farmer;
import com.farm.domain.entity.Land;

@Projection(
		  name = "landProjection", 
		  types = { Land.class }) 
public interface LandProjection {

	@Value("#{target.landId}")
	Integer getLandId();
	
	String getSurveyNo();
	
	LandSize getLandSize();
	
	Address getAddress();
	
	Integer getNumberOfFarms();
	
	Set<Farmer> getFarmers();
	
	String getDescription();
	
	String getLatitude();
	
	String getLongitude();
}
