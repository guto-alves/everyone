package com.gutotech.everyone.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SaleItem {
	@EmbeddedId
	private SaleItemId id = new SaleItemId();

	private int quantity = 1;

	private double price;

	private int discount;

	public SaleItem() {
	}

	public SaleItem(Sale sale, Product product, int quantity, double price, int discount) {
		id.setSale(sale);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}

	public double getSubTotal() {
		return (price * (1 - discount / 100)) * quantity;
	}

	@JsonIgnore
	public Sale getSale() {
		return id.getSale();
	}

	public void setSale(Sale sale) {
		this.id.setSale(sale);
	}

	public Product getClothe() {
		return id.getProduct();
	}

	public void setClothe(Product product) {
		this.id.setProduct(product);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SaleItem)) {
			return false;
		}
		SaleItem other = (SaleItem) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
