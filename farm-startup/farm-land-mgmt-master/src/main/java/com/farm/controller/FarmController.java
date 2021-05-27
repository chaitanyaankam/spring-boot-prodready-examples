package com.farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.domain.model.FarmBooking;
import com.farm.domain.model.LandFarmAssociation;
import com.farm.domain.model.Response;
import com.farm.service.FarmService;
import com.farm.util.FarmConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/${api.version}", produces=MediaType.APPLICATION_JSON_VALUE)
@Api(value="farm-land management", description="Operations pertaining to farm management", produces=MediaType.APPLICATION_JSON_VALUE)
public class FarmController {
	
	@Autowired
	private FarmService farmservice;

	@ApiOperation(value = "Api to create available farms | Land and Farms Association")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Saved Association"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(value="/asoc/createFarms")
	public ResponseEntity<?> createAvailableFarms(@RequestBody LandFarmAssociation association) throws Exception{
		farmservice.createAvailableFarms(association);
		Response response =  Response.builder().status(FarmConstants.ASSOCIATION_SUCCESS_STATUS)
				.message(FarmConstants.ASSOCIATION_SUCCESS_MSG).build();
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Api for booking a farm | User, farm and Land Association")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Saved Association"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(value="/asoc/bookFarm")
	public ResponseEntity<?> saveFarmBooking(@RequestBody FarmBooking farmBooking) throws Exception{
		farmservice.saveFarmBooking(farmBooking);
		Response response =  Response.builder().status(FarmConstants.SAVE_SUCCESS_STATUS)
				.message(FarmConstants.SAVE_SUCCESS_MSG).build();
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
