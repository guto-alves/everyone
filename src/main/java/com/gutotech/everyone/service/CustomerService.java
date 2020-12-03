package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Cart;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Customer> findAll() {
		return repository.findAll();
	}

	public Customer findById(long id) {
		return repository.findById(id).get();
	}

	public Customer findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public void save(Customer customer) {
		repository.save(customer);
	}

	public void register(Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setRole("ROLE_CUSTOMER");
		customer.setEnabled(true);
		
		repository.save(customer);
		
		Cart cart = new Cart(customer);
		customer.setCart(cart);
		
		repository.save(customer);
	}

	public boolean emailExists(final String email) {
		return repository.findByEmail(email) != null;
	}

	public boolean checkIfValidOldPassword(final Customer customer, final String oldPassword) {
		return passwordEncoder.matches(oldPassword, customer.getPassword());
	}

	public void changePassword(final Customer customer, final String password) {
		customer.setPassword(passwordEncoder.encode(password));
		repository.save(customer);
	}
}
