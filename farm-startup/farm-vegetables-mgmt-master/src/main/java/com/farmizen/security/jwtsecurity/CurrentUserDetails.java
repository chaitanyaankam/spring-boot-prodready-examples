package com.farmizen.security.jwtsecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.farmizen.exception.JwtAuthenticationException;
import com.farmizen.security.jwtsecurity.model.JwtUserDetails;

public class CurrentUserDetails {

	private static Logger logger = LoggerFactory.getLogger(CurrentUserDetails.class);
	
	public static JwtUserDetails getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof JwtUserDetails) {
			JwtUserDetails userdetails = (JwtUserDetails) principal;
			logger.debug("Logged in User from SecurityContextHolder {} ", userdetails.getUsername());
			return userdetails;
		} else 
			throw new JwtAuthenticationException("User Authentication missing in the context");
	}
	
}
