package com.farm.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.entity.Farm;
import com.farm.domain.entity.FarmOwner;
import com.farm.domain.entity.Land;
import com.farm.domain.model.FarmBooking;
import com.farm.domain.model.LandFarmAssociation;
import com.farm.exception.FarmBookingException;
import com.farm.repository.FarmOwnerRepository;
import com.farm.repository.FarmRepository;
import com.farm.repository.LandRepository;
import com.farm.security.jwtsecurity.CurrentUserDetails;
import com.farm.security.jwtsecurity.model.JwtUserDetails;
import com.farm.util.NameUtil;

@Service
public class FarmServiceImpl implements FarmService {

	@Autowired
	private LandRepository landRepository;

	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private FarmOwnerRepository farmOwnerRepository;

	@Autowired
	private CommonService commonService;

	private static final String LAND_NOT_AVAILABLE = "Land not available";

	private static final String FARM_BOOKING_FAILURE_OWNER_MSG = "Owner not found";

	private static final String FARM_BOOKING_FAILUR_LANDOR_MFARM_MSG = "land or farm not found";
	
	private static final String FARM_BOOKING_FAILUR_ALREADY_BOOKED = "Farm doesn't exists (or) Already booked";

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public void createAvailableFarms(LandFarmAssociation association) throws Exception {
		int numberOfPlots = association.getNumberOfplots();
		Optional<Land> optionalLand = Optional.of(landRepository.findOne(association.getLandId()));
		if (optionalLand.isPresent()) {
			// int existingFarmsCount = optionalLand.get().getFarms().size();
			for (int i = 0; i < numberOfPlots; i++) {
				// String farmName = FarmConstants.FARM_NAMES[existingFarmsCount+i];
				String farmName = NameUtil.getAlphaNumericString(15);
				Farm newFarm = Farm.builder().farmId(farmName).size(association.getSize())
						.land(optionalLand.get()).isBooked(false)
						.build();
				farmRepository.save(newFarm);
			}
		} else {
			throw new ResourceNotFoundException(LAND_NOT_AVAILABLE);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public void saveFarmBooking(FarmBooking booking) throws Exception {
		Optional<Land> optionalLand = Optional.ofNullable(landRepository.findOne(booking.getLandId()));
		Optional<Farm> optionalFarm = Optional.ofNullable(farmRepository.findOne(booking.getFarmId()));
		JwtUserDetails loggedInUser = CurrentUserDetails.getLoggedInUser();
		Optional<FarmOwner> optionalFarmOwner = Optional.ofNullable(farmOwnerRepository.findOne(loggedInUser.getId()));
		/* if logged in user is not farm owner --> insert his record into farm owner 
		 * we are making him farm owner
		 */
		if (!optionalFarmOwner.isPresent()) {
			// check if the logged In user is a valid user by querying in the user-mgmt service
			Optional<JSONObject> user = Optional.ofNullable(commonService.getLoggedInUser());
			if (user.isPresent()) {
				FarmOwner newFarmOwner = FarmOwner.builder().plotOwnerId(loggedInUser.getId()).build();
				optionalFarmOwner = Optional.ofNullable(farmOwnerRepository.save(newFarmOwner));
			}
		}

		if (optionalFarmOwner.isPresent() && optionalLand.isPresent() 
				&& optionalFarm.isPresent() && !optionalFarm.get().getIsBooked()) {
			Farm farmUnderBooking = optionalFarm.get();
			farmUnderBooking.setFarmOwner(optionalFarmOwner.get());
			farmUnderBooking.setIsBooked(true);
			farmRepository.save(farmUnderBooking);
		} else if (!optionalFarmOwner.isPresent()) {
			throw new ResourceNotFoundException(FARM_BOOKING_FAILURE_OWNER_MSG);
		} else if(optionalFarm.isPresent() && optionalFarm.get().getIsBooked()){
			throw new FarmBookingException(FARM_BOOKING_FAILUR_ALREADY_BOOKED);
		} else {
			throw new ResourceNotFoundException(FARM_BOOKING_FAILUR_LANDOR_MFARM_MSG);
		}
	}

}
