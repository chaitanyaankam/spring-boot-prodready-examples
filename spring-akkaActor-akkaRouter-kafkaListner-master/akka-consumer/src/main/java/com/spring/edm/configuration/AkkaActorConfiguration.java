package com.spring.edm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.edm.actor.DeserializeActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

@Configuration
public class AkkaActorConfiguration {
	
	@Value("${actor-pool.size}")
	private int actorPoolSize;

	@Bean
    public ActorSystem actorSystem() {
       return ActorSystem.create("akka-pojo-generator");	
    }
	
	@Bean
	public ActorRef actorRef() {
		return actorSystem().actorOf(pool().props(Props.create(DeserializeActor.class)), 
			    "akka-pojo-generator.router");
	}
	
	@Bean
	public RoundRobinPool pool() {
		return new RoundRobinPool(actorPoolSize);
	}
}
