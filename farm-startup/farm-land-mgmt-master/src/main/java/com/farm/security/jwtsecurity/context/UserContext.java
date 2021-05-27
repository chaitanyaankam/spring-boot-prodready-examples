package com.farm.security.jwtsecurity.context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
	
    public static final String CORRELATION_ID = "tmx-correlation-id";
    
    private String correlationId;
    
    private String authToken;

    public String getCorrelationId() {
    	return correlationId;
    }
    
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    
}