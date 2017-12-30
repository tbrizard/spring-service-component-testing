package com.blizzardcomputing.cart.persistence;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.blizzardcomputing.cart.service.model.Cart;
import com.blizzardcomputing.cart.service.model.Item;

@Repository
public class CartRepository { 

	// if this was a real service we would make this an interface and extend CrudRepository and read the cart 
	// from a database. but for this example we are just returning a stubbed cart to show how we would use Wiremock
	// for mocking dependencies when running component tests.
	
	
	public Cart readCart(Long cartId) {
		if (cartId == 99) {
			return null;  // simulate cart not being found
		}
		
		Cart cart = new Cart();
		cart.setId(cartId);
		cart.setItems(new ArrayList<>());
		
		// add a dummy item
		Item item = new Item();
		item.setId(21323293803l);
		cart.getItems().add(item);
		if (cartId == 1) {
			item.setProductId("ASAS2-D343AA");
		} else {
			item.setProductId("FF9A1-G355LP");
		}
		
		return cart;
	}
}
