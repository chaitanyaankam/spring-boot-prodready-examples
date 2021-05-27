package com.store.bookservice.service;

import com.store.bookservice.domain.Order;

public interface OrderService {

	public Order getOrder() throws Exception;
	
	public String getProductInformation(String productName) throws Exception;
	
}
