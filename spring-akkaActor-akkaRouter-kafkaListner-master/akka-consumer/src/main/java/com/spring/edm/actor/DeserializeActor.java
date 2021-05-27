package com.spring.edm.actor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.edm.bean.Message;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class DeserializeActor extends AbstractActor {
	
	private static Logger logger = LoggerFactory.getLogger(DeserializeActor.class);
	
	public static Props props() { //props factory
        return Props.create(AbstractActor.class);
    }
    
	public Receive createReceive() {
        return receiveBuilder().match(Object.class, this::onMessage).build();
    }
    
    private void onMessage(Object messageBytes) throws ClassNotFoundException, IOException {
    	byte[] objectBytes = (byte[]) messageBytes;
    	Message message = deserialize(objectBytes);
    	logger.info("message with information {} ", message);
    }
    
    public Message deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return (Message) is.readObject();
    }
}