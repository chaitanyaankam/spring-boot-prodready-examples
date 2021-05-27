package com.farm.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.farm.domain.entity.Land;

public class LandValidators implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(LandValidators.class);
	 
    @Override
    public boolean supports(Class<?> clazz) {
        return Land.class.equals(clazz);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
    	Land land = (Land) obj;
    	logger.info("In Land Validator for land survey no: {}",land.getSurveyNo());
    	//errors.rejectValue("surveyNo", "surveyNo.empty");
    }	
}
