package com.spring.edm.serializer;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.edm.bean.Message;

public class PayloadSerializer implements Serializer<Object>{
	
	private static Logger LOG = LoggerFactory.getLogger(PayloadSerializer.class);

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {	
		//DO NOTHING
	}

	@Override
	public byte[] serialize(String topic, Object data) {
		byte[] b = null;
		try {
			LOG.info("Unmasrshalling data {} ", data);
			// unmarshalling String to Java Object
			JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(data.toString());
			Message messgaeObject = (Message) unmarshaller.unmarshal(reader);

			// sending serialized java Object to kafka producer
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(messgaeObject);
			oos.close();
			b = baos.toByteArray();			
		} catch(Exception e) {
			LOG.error("Exception while Unmarshalling and Serializing ", e);
		}	
		return b;
	}

	@Override
	public void close() {
		//DO NOTHING
	}

}
