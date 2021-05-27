package com.mkyong;

import java.util.Map;

import org.elasticsearch.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootApplication
public class Application extends SpringBootServletInitializer  {

	public static void main(String args[]) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		Client client = context.getBean(ElasticsearchOperations.class).getClient();
		Map<String, String> asMap = client.settings().getAsMap();
		asMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});
		System.out.println("<--ElasticSearch--");
	}

}