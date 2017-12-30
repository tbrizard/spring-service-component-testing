package com.blizzardcomputing.cart.service.model;

public class Item {

	private Long id;
	private String productId;
	private Double price;  // would create a Price object with properties of a Price (i.e. total, base, tax, etc.)
	
	// other properties, like quantity, etc. ...
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
