package com.farm.security.jwtsecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.farm.security.jwtsecurity.context.UserContext;
import com.farm.security.jwtsecurity.context.UserContextHolder;
import com.farm.security.jwtsecurity.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
	
	public JwtAuthenticationTokenFilter() {
		super("/api/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		String header = httpServletRequest.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer "))
			throw new RuntimeException("JWT Token is missing");
		String authenticationToken = header.substring(7);
		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		// on successful authentication place token in the UserContextHolder
		UserContextHolder.getContext().setCorrelationId(request.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setAuthToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        logger.debug("Special Routes Service Incoming Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername()
				: principal.toString();
		logger.debug("Logged in User from SecurityContextHolder {} ", username);
		chain.doFilter(request, response);
	}
}
