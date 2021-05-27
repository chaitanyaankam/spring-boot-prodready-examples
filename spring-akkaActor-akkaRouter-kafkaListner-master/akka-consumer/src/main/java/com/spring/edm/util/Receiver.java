package com.spring.edm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import akka.actor.ActorRef;

public class Receiver {
	
	@Autowired
	private ActorRef router;

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@KafkaListener(topics = "topicA01")
	public void receive(byte[] payload) {
		LOGGER.debug("received payload= {}", payload);
		router.tell(payload, router);
	}

}
