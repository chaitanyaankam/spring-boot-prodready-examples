package com.store.bookservice.domain;

import java.util.List;

public class Order {

	private String id;
	
	private List<OrderItem> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
}
