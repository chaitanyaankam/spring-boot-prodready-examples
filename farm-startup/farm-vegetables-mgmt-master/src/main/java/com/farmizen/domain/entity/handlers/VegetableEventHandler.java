package com.farmizen.domain.entity.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.farmizen.domain.entity.Vegetable;
import com.fasterxml.jackson.core.JsonProcessingException;

@RepositoryEventHandler(Vegetable.class) 
public class VegetableEventHandler {

	private Logger logger = LoggerFactory.getLogger(VegetableEventHandler.class);	

	@HandleBeforeCreate
	public void handLandSave(Vegetable faqCategory) throws JsonProcessingException {
		logger.info("before creating vegetable: \n{} ", faqCategory.toJsonString());
	}

	@HandleAfterCreate
	public void handleAuthorAfterCreate(Vegetable faqCategory) throws JsonProcessingException {
		logger.info("After creating vegetable: \n{} ", faqCategory.toJsonString());
	}

}