package com.farm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.domain.model.LandOwnerAssociation;
import com.farm.domain.model.Response;
import com.farm.service.LandService;
import com.farm.util.FarmConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/${api.version}", produces=MediaType.APPLICATION_JSON_VALUE)
@Api(value="farm-land management", description="Operations pertaining to land management", produces=MediaType.APPLICATION_JSON_VALUE)
public class LandController {

	@Autowired
	private LandService landService;
	
	@ApiOperation(value = "Api for saving Land and Owners Association")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Saved Land"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(value="/asoc/lands")
	public ResponseEntity<?> saveAssociationOfLandAndOwner(@RequestBody LandOwnerAssociation association) throws Exception {
		landService.saveLandOwnerAssociation(association);
		Response response =  Response.builder().status(FarmConstants.ASSOCIATION_SUCCESS_STATUS)
				.message(FarmConstants.ASSOCIATION_SUCCESS_MSG).build();
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Api for saving Farmer and Land Association, Input is Map<LandId, FarmerId>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Saved Farmer Land Association"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(value="/asoc/farmer")
	public ResponseEntity<?> saveAssociationOfFarmerToLand(@RequestBody Map<Integer, String> landAndFarmerAscoMap) throws Exception {
		landService.saveFarmerAndLandAssociation(landAndFarmerAscoMap);
		Response response =  Response.builder().status(FarmConstants.ASSOCIATION_SUCCESS_STATUS)
				.message(FarmConstants.ASSOCIATION_SUCCESS_MSG).build();
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
}
