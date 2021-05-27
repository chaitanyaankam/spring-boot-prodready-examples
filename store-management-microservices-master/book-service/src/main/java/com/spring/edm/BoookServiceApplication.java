package com.spring.edm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan( basePackages = {"com.spring.edm.entity"} )
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class BoookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoookServiceApplication.class, args);
	}
}
