package com.spring.edm.domain;

import com.spring.edm.entity.Genre;

import lombok.Data;

@Data
public class BookDTO {

	private int id;

	private String name;

	private int cost;
	
	private Genre genre;
}
