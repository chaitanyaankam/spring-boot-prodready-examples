package com.store.bookservice.domain;

import net.minidev.json.JSONObject;

public class OrderItem {
	
	private int productid;

	private JSONObject productInfo;
	
	private int quantity;

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public JSONObject getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(JSONObject productInfo) {
		this.productInfo = productInfo;
	}
	
}
