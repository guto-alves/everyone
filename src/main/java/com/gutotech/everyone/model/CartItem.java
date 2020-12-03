package com.gutotech.everyone.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CartItem {
	@EmbeddedId
	private CartItemId id = new CartItemId();

	private int quantity = 1;

	public CartItem() {
	}

	public CartItem(Cart cart, Clothe clothe, int quantity) {
		id.setCart(cart);
		id.setClothe(clothe);
		this.quantity = quantity;
	}

	public Cart getCart() {
		return id.getCart();
	}

	public void setCart(Cart cart) {
		this.id.setCart(cart);
	}

	public Clothe getClothe() {
		return id.getClothe();
	}

	public void setClothe(Clothe clothe) {
		this.id.setClothe(clothe);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
