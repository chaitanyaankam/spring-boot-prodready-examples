package com.spring.edm.configuration;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author achaitanya
 * Creating a configuration for inmemory-queue
 * This queue is pushed with some XML messagaes
 */
@Configuration
@EnableJms
public class QueueConfig {

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("inmemory.queue");
    }
    
}