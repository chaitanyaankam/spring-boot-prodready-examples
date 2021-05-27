package com.spring.edm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.edm.entity.Genre;
import com.spring.edm.service.GenreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins="http://localhost:4200", maxAge = 4800)
@RestController
@Api(value="onlinestore", description="Operations pertaining to Genre in Books Store")
public class GenreController {
	
	@Autowired
	private GenreService genreService;

	@ApiOperation(value = "View a list of available book genre")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value="/api/genre")
	public ResponseEntity<?> getAllGenres(){
		Optional<List<Genre>> optionalGenre = Optional.of(genreService.findAllGenre());
		return new ResponseEntity<>(optionalGenre.get(), HttpStatus.OK);
	}
}

