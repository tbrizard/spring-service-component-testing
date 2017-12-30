package com.blizzardcomputing.cart.persistence.dto;

public class PriceDTO {

	private Double totalPrice;

	// other properties, like base price, tax, etc.
	
	
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
