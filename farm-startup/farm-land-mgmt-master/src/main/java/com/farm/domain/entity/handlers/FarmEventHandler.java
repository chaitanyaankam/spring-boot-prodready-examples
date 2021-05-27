package com.farm.domain.entity.handlers;

import java.text.MessageFormat;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.farm.domain.entity.Farm;
import com.farm.exception.ApiCallException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RepositoryEventHandler(Farm.class) 
public class FarmEventHandler {
	
	@Value("${services.vegetables}")
	private String baseVegetablesServiceURL;

	@Autowired
	@Qualifier("jwtRestTemplate")
	private RestTemplate restTemplate;
	
	private final String VEGETABLE_NOTFOUND = "vegatables with id {0} not found";
	
	private final String FARM_NOTFOUND = "farm with id {0} not found";
	

	private Logger logger = LoggerFactory.getLogger(FarmEventHandler.class);	

	@HandleBeforeCreate
	@HystrixCommand(
			fallbackMethod = "handleLandSave_fallback",
			threadPoolKey = "handleLandSave_pool",
			threadPoolProperties ={
				@HystrixProperty(name = "coreSize",value="3"),
				@HystrixProperty(name="maxQueueSize", value="2"),
			},
			commandProperties ={
				@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
				@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
				@HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
			})
	public void handleLandSave(Farm farm) throws JsonProcessingException {
		farm.setIsBooked(false);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		Set<String> farmVegeTables = farm.getVegetables();
		for(String vegetableId: farmVegeTables) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseVegetablesServiceURL)
					.fragment(vegetableId);
			ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Object.class);
			if(!response.getStatusCode().is2xxSuccessful())
				throw new ApiCallException(MessageFormat.format(VEGETABLE_NOTFOUND, farm.getFarmId()));
			
		}
		logger.info("before creating Land info: \n{} ",farm.toJsonString());
	}
	
	public void handleLandSave_fallback(Farm farm) throws JsonProcessingException {
		farm.setIsBooked(false);
		logger.info("fallback_handleLandSave {} ",farm.toJsonString());
	}

	@HandleAfterCreate
	public void handleLandAfterCreate(Farm farm) throws JsonProcessingException {
		logger.info("After creating Land info: \n{} ",farm.toJsonString());
	}

	@HandleBeforeSave
	@HystrixCommand(
			fallbackMethod = "handleLandBeforeUpdate_fallback",
			threadPoolKey = "handleLandBeforeUpdate_pool",
			threadPoolProperties ={
				@HystrixProperty(name = "coreSize",value="3"),
				@HystrixProperty(name="maxQueueSize", value="2"),
			},
			commandProperties ={
				@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
				@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
				@HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
			})
	public void handleLandBeforeUpdate(Farm farm) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		Set<String> farmVegeTables = farm.getVegetables();
		for(String vegetableId: farmVegeTables) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseVegetablesServiceURL)
					.fragment(vegetableId);
			ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Object.class);
			if(!response.getStatusCode().is2xxSuccessful())
				throw new ApiCallException(MessageFormat.format(VEGETABLE_NOTFOUND, farm.getFarmId()));
		}
		logger.info("before updating Land info: \n{} ",farm.toJsonString());
	}
	
	public void handleLandBeforeUpdate_fallback(Farm farm) throws JsonProcessingException {
		logger.info("before updating Land info: \n{} ",farm.toJsonString());
	}
}