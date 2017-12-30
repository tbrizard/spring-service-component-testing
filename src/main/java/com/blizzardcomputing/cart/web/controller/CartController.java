package com.blizzardcomputing.cart.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blizzardcomputing.cart.service.CartService;
import com.blizzardcomputing.cart.service.model.Cart;
import com.blizzardcomputing.cart.service.model.Item;
import com.blizzardcomputing.cart.web.model.CartResource;
import com.blizzardcomputing.cart.web.model.ItemResource;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;


    @RequestMapping("/carts/{cart-id}")
    public CartResource getCart(@PathVariable(name="cart-id") Long cartId) {
        
    	Cart cart = cartService.retrieveCart(cartId);
    	
    	if (cart == null) {
    		 throw new ResourceNotFoundException();
    	}
    	
    	return convertToResource(cart);
    }
    
    private CartResource convertToResource(Cart cart) {
    	
    	CartResource res = new CartResource();
    	List<ItemResource> items = new ArrayList<>();
    	res.setItems(items);
    	res.setId(cart.getId());
    	
    	if (cart.getItems() != null) {
    		for (Item nextItem : cart.getItems()) {
				ItemResource item = new ItemResource();
				item.setId(nextItem.getId());
				item.setProductId(nextItem.getProductId());
				item.setPrice(nextItem.getPrice());
				items.add(item);
			}
    	}
    	
    	return res;
    }
}
