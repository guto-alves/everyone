package com.gutotech.everyone.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ClotheSizeId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Clothe clothe;

	@ManyToOne
	private Size size;

	public Clothe getClothe() {
		return clothe;
	}

	public void setClothe(Clothe clothe) {
		this.clothe = clothe;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clothe == null) ? 0 : clothe.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		ClotheSizeId other = (ClotheSizeId) obj;
		if (clothe == null) {
			if (other.clothe != null)
				return false;
		} else if (!clothe.equals(other.clothe))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
}
