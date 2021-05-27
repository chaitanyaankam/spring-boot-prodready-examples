package com.farm.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.farm.security.jwtsecurity.JwtAuthenticationEntryPoint;
import com.farm.security.jwtsecurity.JwtAuthenticationProvider;
import com.farm.security.jwtsecurity.JwtAuthenticationTokenFilter;
import com.farm.security.jwtsecurity.JwtSuccessHandler;
import com.farm.security.jwtsecurity.RestTemplateInterceptor;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;
    
    @Autowired
    private RestTemplateInterceptor restTemplateInterceptor;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("**/api/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
        http.headers().frameOptions().sameOrigin();
    }
    
    @Bean(name="jwtRestTemplate")
    @LoadBalanced
	public RestTemplate restTemplate() {
    	RestTemplate restTemplate =  new RestTemplate();
    	List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(restTemplateInterceptor);
        restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
}
