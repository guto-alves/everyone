package com.gutotech.everyone.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToOne
	private Customer customer;

	@OneToMany(mappedBy = "id.cart", fetch = FetchType.EAGER)
	private Set<CartItem> items = new HashSet<>();

	public Cart() {
	}

	public Cart(Customer customer) {
		this.customer = customer;
	}

	public Cart(Long id, Customer customer) {
		this.id = id;
		this.customer = customer;
	}

	public boolean contains(Clothe clothe) {
		return items.stream().filter(item -> item.getClothe().equals(clothe)).limit(1).count() == 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer cliente) {
		this.customer = cliente;
	}

	public Set<CartItem> getItems() {
		return items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Cart other = (Cart) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
