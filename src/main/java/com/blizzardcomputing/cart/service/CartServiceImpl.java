package com.blizzardcomputing.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blizzardcomputing.cart.persistence.CartRepository;
import com.blizzardcomputing.cart.persistence.PriceDAO;
import com.blizzardcomputing.cart.persistence.dto.PriceDTO;
import com.blizzardcomputing.cart.service.model.Cart;
import com.blizzardcomputing.cart.service.model.Item;

@Service
public class CartServiceImpl implements CartService {
	 
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private PriceDAO priceDAO;
	
	@Override
	public Cart retrieveCart(Long cartId) {
		if (cartId == null) {
			return null;
		}
		
		Cart foundCart = cartRepo.readCart(cartId);
		
		// to show how we would use Wiremock, we will make a call to get the price for each product
		if (foundCart != null && foundCart.getItems() != null) {
			for (Item nextItem : foundCart.getItems()) {
				PriceDTO price = priceDAO.getProductPrice(nextItem.getProductId());
				if (price != null) {
					nextItem.setPrice(price.getTotalPrice());
				}
			}
		}
		
		return foundCart;
	}

	@Override
	public void saveCart(Cart cart) {
		
		// ... save the cart.. 
	}

}
