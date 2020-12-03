package com.gutotech.everyone.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class SaleItemId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Sale sale;

	@ManyToOne
	private Clothe clothe;

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
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
		result = prime * result + ((clothe == null) ? 0 : clothe.hashCode());
		result = prime * result + ((sale == null) ? 0 : sale.hashCode());
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
		SaleItemId other = (SaleItemId) obj;
		if (clothe == null) {
			if (other.clothe != null)
				return false;
		} else if (!clothe.equals(other.clothe))
			return false;
		if (sale == null) {
			if (other.sale != null)
				return false;
		} else if (!sale.equals(other.sale))
			return false;
		return true;
	}
}
