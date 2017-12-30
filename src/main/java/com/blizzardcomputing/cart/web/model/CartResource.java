package com.blizzardcomputing.cart.web.model;

import java.util.List;

public class CartResource {

    private long id;
    private List<ItemResource> items;
  

    public long getId() {
        return id;
    }

	public void setId(long id) {
		this.id = id;
	}

	public List<ItemResource> getItems() {
		return items;
	}

	public void setItems(List<ItemResource> items) {
		this.items = items;
	}

}
