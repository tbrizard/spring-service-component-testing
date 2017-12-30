package com.blizzardcomputing.cart.service;

import com.blizzardcomputing.cart.service.model.Cart;

public interface CartService {

	public Cart retrieveCart(Long cartId);
	
	public void saveCart(Cart cart);
	
	// other methods ...
}
