package com.gutotech.everyone.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.CartItem;
import com.gutotech.everyone.repository.CartItemRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository repository;

	public void save(CartItem item) {
		repository.save(item);
	}

	public void saveAll(Iterable<CartItem> items) {
		repository.saveAll(items);
	}

	public void delete(CartItem cartItem) {
		repository.delete(cartItem);
	}

	public void deleteAll(Set<CartItem> items) {
		repository.deleteAll(items);
	}

}
