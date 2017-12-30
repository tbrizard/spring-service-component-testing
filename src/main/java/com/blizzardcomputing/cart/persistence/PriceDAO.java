package com.blizzardcomputing.cart.persistence;

import com.blizzardcomputing.cart.persistence.dto.PriceDTO;

public interface PriceDAO {

	public PriceDTO getProductPrice(String productId);
}
