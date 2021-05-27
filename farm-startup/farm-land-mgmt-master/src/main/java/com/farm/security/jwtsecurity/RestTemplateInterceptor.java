package com.farm.security.jwtsecurity;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.farm.security.jwtsecurity.context.UserContext;
import com.farm.security.jwtsecurity.context.UserContextHolder;
import com.farm.security.jwtsecurity.model.JwtUser;
import com.farm.security.jwtsecurity.model.JwtUserDetails;

@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
	
	@Autowired
	JwtValidator validator;
	
	@Autowired
	JwtGenerator generator;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String token = UserContextHolder.getContext().getAuthToken();
		String authorizationToken = token.substring(7);
		Optional<JwtUser> user = Optional.ofNullable(validator.validate(authorizationToken));
		if(!user.isPresent()) {
			JwtUserDetails currentUserdetails =  CurrentUserDetails.getLoggedInUser();
			token = generator.createToken(currentUserdetails);
		}
		HttpHeaders headers = request.getHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, token);
		headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
		return execution.execute(request, body);
	}

}