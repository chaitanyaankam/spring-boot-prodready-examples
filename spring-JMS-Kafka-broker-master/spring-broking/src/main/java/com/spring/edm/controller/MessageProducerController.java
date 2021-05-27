package com.spring.edm.controller;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.edm.util.QueueConsumer;

@RestController
public class MessageProducerController {

	@Autowired
	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	private static Logger LOG = LoggerFactory.getLogger(QueueConsumer.class);

	@PostMapping(value="/message")
	public String publishString(@RequestBody final String message){
		LOG.info("post message received-> {} ", message);
		jmsTemplate.convertAndSend(queue, message);
		return "published succesfully";
	}

}
