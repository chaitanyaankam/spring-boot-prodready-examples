package com.spring.edm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.edm.entity.StoreEntity;
import com.spring.edm.exception.ResourceNotFoundException;
import com.spring.edm.service.EntityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins="http://localhost:4200", maxAge = 4800)
@RestController
@Api(value="onlinestore", description="Operations pertaining to Entities in Store")
public class EntityController {
	
	@Autowired
	private EntityService entityService;

	@ApiOperation(value = "View a list of available store entities")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value="/api/entities")
	public ResponseEntity<?> getAllEntities(){
		Optional<List<StoreEntity>> optionalEntity = Optional.of(entityService.findAllEntities());
		return new ResponseEntity<>(optionalEntity.get(), HttpStatus.OK);
	}
	
	@GetMapping(value="/api/entities/{id}")
	@ApiOperation(value = "View a list of available books with given id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public StoreEntity getEntityViewById(@PathVariable(name="id") final int entityId){
		Optional<StoreEntity> optionalEntity = entityService.findOneWithViewsById(entityId);
		return optionalEntity.orElseThrow(ResourceNotFoundException::new);
	}
	
}

