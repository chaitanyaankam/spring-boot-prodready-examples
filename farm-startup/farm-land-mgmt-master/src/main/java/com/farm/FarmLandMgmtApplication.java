package com.farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCircuitBreaker
@EnableHystrixDashboard
@RefreshScope
public class FarmLandMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmLandMgmtApplication.class, args);
	}

}