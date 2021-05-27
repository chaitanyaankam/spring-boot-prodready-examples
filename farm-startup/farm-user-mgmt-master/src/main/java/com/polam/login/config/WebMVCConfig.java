package com.polam.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Source: https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/
 * 
 * Description: 
 * Enable CORS so that our frontend client can access the APIs from a different origin. 
 * Enabled all origins in the following configuration. 
 * TO DO:// But should make it more strict in a production application 
 **/
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(MAX_AGE_SECS);
    }
}