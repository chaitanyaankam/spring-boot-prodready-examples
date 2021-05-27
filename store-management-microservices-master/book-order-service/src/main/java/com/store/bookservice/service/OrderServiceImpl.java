package com.store.bookservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.store.bookservice.domain.Order;
import com.store.bookservice.domain.OrderItem;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${order-service.productInfo.app.uri}")
	String productInfoUri;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Order getOrder() throws Exception {
		List<OrderItem> items = new ArrayList<>();
		
		OrderItem item1 = new OrderItem();
		item1.setQuantity(1);
		item1.setProductid(1);
		
		items.add(item1);
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setItems(items);
		
		String productInfoJsonString = this.getProductInformation("Life of Pi");
		JSONObject productInfoJson = (JSONObject) JSONValue.parse(productInfoJsonString);
		item1.setProductInfo(productInfoJson);
		
		return order;
	}

	@Override
	public String getProductInformation(String productName) throws Exception {
		ResponseEntity<String> restExchange = restTemplate.exchange(productInfoUri, HttpMethod.GET, null, String.class, productName); 
		return restExchange.getBody();
	}

	
}
