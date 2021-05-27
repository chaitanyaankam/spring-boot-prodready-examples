package com.spring.edm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.edm.entity.Publisher;
import com.spring.edm.service.PublisherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins="http://localhost:4200", maxAge = 4800)
@RestController
@Api(value="onlinestore", description="Operations pertaining to Publishers in Store")
public class PublisherController {
	
	@Autowired
	private PublisherService publisherService;

	@ApiOperation(value = "View a list of available book publishers")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value="/api/publishers")
	public ResponseEntity<?> getAllPublishers(){
		Optional<List<Publisher>> optionalPublishers = Optional.of(publisherService.findAllPublishers());
		return new ResponseEntity<>(optionalPublishers.get(), HttpStatus.OK);
	}
}

