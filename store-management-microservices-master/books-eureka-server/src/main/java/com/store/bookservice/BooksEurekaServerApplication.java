package com.store.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BooksEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksEurekaServerApplication.class, args);
	}

}

