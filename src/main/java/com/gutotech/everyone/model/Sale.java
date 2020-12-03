package com.gutotech.everyone.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Basic
	@Temporal(TemporalType.DATE)
	private Date date;

	private String status;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private CreditCard card;

	@OneToMany(mappedBy = "id.sale")
	private Set<SaleItem> items = new HashSet<>();

	public Sale() {
	}

	public Sale(Date date, String status, Customer customer) {
		this.date = date;
		this.status = status;
		this.customer = customer;
	}

	public Sale(Long id, Date date, String status, Customer customer) {
		this.id = id;
		this.date = date;
		this.status = status;
		this.customer = customer;
	}

	public double getTotal() {
		return items.stream()
					.mapToDouble(SaleItem::getSubTotal)
					.sum();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public Set<SaleItem> getItems() {
		return items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sale)) {
			return false;
		}
		Sale other = (Sale) obj;
		return id == other.id;
	}
}
