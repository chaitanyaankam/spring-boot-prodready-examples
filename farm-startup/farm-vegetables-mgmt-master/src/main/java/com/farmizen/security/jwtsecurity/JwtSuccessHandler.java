package com.farmizen.security.jwtsecurity;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class JwtSuccessHandler implements AuthenticationSuccessHandler{
	
	private static Logger logger = LoggerFactory.getLogger(JwtSuccessHandler.class);
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    	logger.info("Successfully Authentication");
    }
}
