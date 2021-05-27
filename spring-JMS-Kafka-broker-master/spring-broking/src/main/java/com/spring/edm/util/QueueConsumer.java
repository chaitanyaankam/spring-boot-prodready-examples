package com.spring.edm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class QueueConsumer {
	
	private static Logger LOG = LoggerFactory.getLogger(QueueConsumer.class);
	
	@Value("${kafka.topic.name}")
	String topicName;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;	 
	
	/**
	 * @author chaitanya
	 * @param message
	 * The following method is used to place a message in the kafka topic
	 */
	@Async
	public void sendMessage(String message) throws Exception{
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, message);
		future.addCallback(
				(final SendResult<String, Object> msg) -> LOG.info("sent message= {} with offset= {}", msg, msg.getRecordMetadata().offset()),
				(final Throwable throwable) -> LOG.error("unable to send message= {}", message, throwable));
		LOG.info("Message Sent to Kafka topic");
	}
	
	/**
	 * @author chaitanya
	 * @param message
	 * The following method is used to listen on a queue
	 * @throws Exception 
	 */
	@JmsListener(destination = "inmemory.queue")
    public void listener(String message) throws Exception{
		LOG.info("Receiveage: -> {}", message);
        this.sendMessage(message);
    }
	
}
