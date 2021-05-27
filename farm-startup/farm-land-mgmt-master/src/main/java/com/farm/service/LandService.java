package com.farm.service;

import java.util.Map;

import com.farm.domain.model.LandOwnerAssociation;

public interface LandService {

	void saveLandOwnerAssociation(LandOwnerAssociation association) throws Exception;

	void saveFarmerAndLandAssociation(Map<Integer, String> landAndFarmerAscoMap) throws Exception;
}
