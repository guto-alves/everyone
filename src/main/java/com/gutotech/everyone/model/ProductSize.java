package com.gutotech.everyone.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductSize {
	@EmbeddedId
	private ProductSizeId id = new ProductSizeId();

	@NotNull
	private int quantity;

	public ProductSize() {
	}

	public ProductSize(Product product, Size size, int quantity) {
		id.setProduct(product);
		id.setSize(size);
		this.quantity = quantity;
	}

	@JsonIgnore
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	@JsonIgnore
	public Size getSize() {
		return id.getSize();
	}

	public void setSize(Size size) {
		id.setSize(size);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProductSize)) {
			return false;
		}
		ProductSize other = (ProductSize) obj;
		return Objects.equals(id, other.id) && quantity == other.quantity;
	}

}
