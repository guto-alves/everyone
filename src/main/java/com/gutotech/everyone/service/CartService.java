package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Cart;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository repository;

	public List<Cart> findAll() {
		return repository.findAll();
	}

	public void save(Cart cart) {
		repository.save(cart);
	}

	public Cart findByCustomer(Customer customer) {
		return repository.findByCustomer(customer);
	}
}
