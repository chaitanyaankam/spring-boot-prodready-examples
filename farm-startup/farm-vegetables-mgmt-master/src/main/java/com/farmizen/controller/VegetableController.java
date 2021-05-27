package com.farmizen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmizen.domain.model.CategoryAssociation;
import com.farmizen.domain.model.Response;
import com.farmizen.service.VegetableService;
import com.farmizen.util.VegetableConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/${api.version}", produces=MediaType.APPLICATION_JSON_VALUE)
@Api(value="vegetables management", description="Operations pertaining to vegetable management", produces=MediaType.APPLICATION_JSON_VALUE)
public class VegetableController {
	
	@Autowired
	private VegetableService vegetableService;

	@ApiOperation(value = "View a list of available lands")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Saved Association"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(value="/asoc/categoryAndVegetable")
	public ResponseEntity<?> saveVegatablesCategory(@RequestBody CategoryAssociation category) throws Exception{
		vegetableService.saveCategoryForVegatable(category);
		Response response =  Response.builder().status(VegetableConstants.ASSOCIATION_SUCCESS_STATUS)
				.message(VegetableConstants.ASSOCIATION_SUCCESS_STATUS).build();
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
