package com.farm.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.entity.Farmer;
import com.farm.domain.entity.Land;
import com.farm.domain.entity.LandOwner;
import com.farm.domain.model.LandOwnerAssociation;
import com.farm.repository.FarmerRepository;
import com.farm.repository.LandOwnerRepository;
import com.farm.repository.LandRepository;

@Service
public class LandServiceImpl implements LandService {

	@Autowired
	private LandRepository landRepository;
	
	@Autowired
	private LandOwnerRepository landOwnerRepository; 
	
	@Autowired 
	private FarmerRepository farmerRepository;
	
	private static final String ASSCO_FAILURE_MSG = "Resource: {0} not found";
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	public void saveLandOwnerAssociation(LandOwnerAssociation association) throws Exception {
		List<LandOwner> landOwners = new ArrayList<>(association.getOwnerId().size());
		Optional<Land> optioanlLand = Optional.ofNullable(landRepository.findOne(association.getLandId()));
		
		//Check if land is present
		if(optioanlLand.isPresent()) {
			for(String ownerId: association.getOwnerId()) {
				Optional<LandOwner> optionalLandOwner = Optional.ofNullable(landOwnerRepository.findOne(ownerId));
				if(optionalLandOwner.isPresent()) landOwners.add(optionalLandOwner.get()); 
			}
		} else
			throw new ResourceNotFoundException(MessageFormat.format(ASSCO_FAILURE_MSG, "Land "+association.getLandId()));
		
		
		//Check if the Owners are present and save association
		if(landOwners.size()>0) {
			landOwners.forEach(owner->{
				owner.getLands().add(optioanlLand.get());
				landOwnerRepository.save(owner);
			});
		} else
			throw new ResourceNotFoundException(MessageFormat.format(ASSCO_FAILURE_MSG, "Land "+association.getLandId()));
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	public void saveFarmerAndLandAssociation(Map<Integer, String> landAndFarmerAscoMap) throws Exception {
		landAndFarmerAscoMap.forEach((landId, farmerId)->{
			Optional<Land> optionalLand = Optional.ofNullable(landRepository.findOne(landId));
			Optional<Farmer> optionalFarmer = Optional.ofNullable(farmerRepository.findOne(farmerId));
			if(optionalLand.isPresent() && optionalFarmer.isPresent()) {
				optionalFarmer.get().getLands().add(optionalLand.get());
				farmerRepository.save(optionalFarmer.get());
			} else if(!optionalLand.isPresent())
				throw new ResourceNotFoundException(MessageFormat.format(ASSCO_FAILURE_MSG, "Land "+landId));
			else if(!optionalFarmer.isPresent())
				throw new ResourceNotFoundException(MessageFormat.format(ASSCO_FAILURE_MSG, "Farmer "+farmerId));
		});
		
		
	}

}
