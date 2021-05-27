package com.store.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * Zuul at its heart is a reverse proxy. A reverse proxy is an intermediate server that sits between 
 * the client trying to reach a resource and the resource itself. The client has no idea it’s even communicating to a server other than a proxy. 
 * The reverse proxy takes care of capturing the client’s request and then calls the remote resource on the client’s behalf.
 * */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy 
/*@EnableZuulProxy - loads any of the Zuul reverse proxy filters or use Netflix Eureka for service discovery.
  Zuul will automatically use Eureka to look up services by their service IDs and then use Netflix Ribbon 
  to do client-side load balancing of requests from within Zuul*/
/*@EnableZuulServer is used when you want to build your own routing service and not use any Zuul pre-built capabilities. 
An example of this would be if you wanted to use Zuul to integrate with a service discovery engine other than Eureka*/
public class BookZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookZuulServiceApplication.class, args);
	}

}

