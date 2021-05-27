package com.spring.edm.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	int id;
    
    String information;
    
    String postedBy;

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	public String getInformation() {
		return information;
	}

	@XmlElement
	public void setInformation(String information) {
		this.information = information;
	}

	public String getPostedBy() {
		return postedBy;
	}

	@XmlElement
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
    
}