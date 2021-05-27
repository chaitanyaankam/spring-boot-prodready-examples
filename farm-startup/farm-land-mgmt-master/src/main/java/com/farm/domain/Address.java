package com.farm.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	private String addressLine1;
	
	private String addressLine2;
	
	private String city;
	
	private String state;
	
	private String postalZipCode;
}
