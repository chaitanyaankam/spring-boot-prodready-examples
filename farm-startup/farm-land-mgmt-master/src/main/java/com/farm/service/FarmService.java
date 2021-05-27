package com.farm.service;

import com.farm.domain.model.FarmBooking;
import com.farm.domain.model.LandFarmAssociation;

public interface FarmService {

	void createAvailableFarms(LandFarmAssociation association) throws Exception;
	
	void saveFarmBooking(FarmBooking booking) throws Exception;
	
}
