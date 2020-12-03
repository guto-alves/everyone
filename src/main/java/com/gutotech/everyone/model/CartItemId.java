package com.gutotech.everyone.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CartItemId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Cart cart;

	@ManyToOne
	private Clothe clothe;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Clothe getClothe() {
		return clothe;
	}

	public void setClothe(Clothe clothe) {
		this.clothe = clothe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((clothe == null) ? 0 : clothe.hashCode());
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
		CartItemId other = (CartItemId) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (clothe == null) {
			if (other.clothe != null)
				return false;
		} else if (!clothe.equals(other.clothe))
			return false;
		return true;
	}
}
