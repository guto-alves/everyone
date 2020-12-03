package com.gutotech.everyone.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ClotheSize {
	@EmbeddedId
	private ClotheSizeId id = new ClotheSizeId();

	@NotNull
	private int quantity;

	public ClotheSize() {
	}

	public ClotheSize(Clothe clothe, Size size, int quantity) {
		id.setClothe(clothe);
		id.setSize(size);
		this.quantity = quantity;
	}

	@JsonIgnore
	public Clothe getClothe() {
		return id.getClothe();
	}

	public void setClothe(Clothe clothe) {
		id.setClothe(clothe);
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
		if (!(obj instanceof ClotheSize)) {
			return false;
		}
		ClotheSize other = (ClotheSize) obj;
		return Objects.equals(id, other.id) && quantity == other.quantity;
	}

}
