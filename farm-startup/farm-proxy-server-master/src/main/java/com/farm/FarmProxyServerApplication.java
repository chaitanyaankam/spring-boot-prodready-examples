package com.farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class FarmProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmProxyServerApplication.class, args);
	}

}
