package com.store.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // Enables the service as a spring cloud configuration server
public class BooksConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksConfigServerApplication.class, args);
	}

}

