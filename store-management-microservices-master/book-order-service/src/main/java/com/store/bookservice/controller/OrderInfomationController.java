package com.store.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.bookservice.domain.Order;
import com.store.bookservice.service.OrderService;

@RestController
public class OrderInfomationController {

	@Autowired
	OrderService orderService;

	@GetMapping(value="/orders")
	public Order getCurrentOrders() throws Exception {
		return orderService.getOrder(); 
	}

}
