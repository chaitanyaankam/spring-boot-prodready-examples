package com.farm.domain.entity.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.farm.domain.entity.Land;
import com.fasterxml.jackson.core.JsonProcessingException;

@RepositoryEventHandler(Land.class) 
public class LandEventHandler {

	private Logger logger = LoggerFactory.getLogger(LandEventHandler.class);	

	@HandleBeforeCreate
	public void handLandSave(Land land) throws JsonProcessingException {
		logger.info("before creating Land info: \n{} ",land.toJsonString());
	}

	@HandleAfterCreate
	public void handleLandAfterCreate(Land land) throws JsonProcessingException {
		logger.info("After creating Land info: \n{} ",land.toJsonString());
	}

}