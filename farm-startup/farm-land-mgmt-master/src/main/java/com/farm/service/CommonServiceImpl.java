package com.farm.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.farm.exception.ApiCallException;
import com.farm.util.FarmConstants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class CommonServiceImpl implements CommonService {

	@Value("${services.user}")
	private String userService;
	
	@Autowired
	@Qualifier("jwtRestTemplate")
	private RestTemplate restTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Override
	@HystrixCommand(
			fallbackMethod = "getLoggedInUser_fallback",
			threadPoolKey = "getLoggedInUser_pool",
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
	public JSONObject getLoggedInUser() throws RuntimeException {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
			String uri = userService+FarmConstants.USER_MGMT_LOGGED_IN_USER_END_POINT;
			ResponseEntity<String> jsonResponse = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
					String.class);
			logger.info("user service call completed {}", jsonResponse.getBody());
			return new JSONObject(jsonResponse.getBody());
		} catch (Exception e) {
			logger.error("Exception making api call ", e);
			throw new ApiCallException(e.getMessage());
		}
	}

	public JSONObject getLoggedInUser_fallback() throws Exception {
		logger.info("FALLBACK: User service Unavailable check in cache");
		return null;
	}
}
