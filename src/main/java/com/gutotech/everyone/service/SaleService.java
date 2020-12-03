package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.model.Sale;
import com.gutotech.everyone.repository.SaleRepository;

@Service
public class SaleService {
	@Autowired
	private SaleRepository repository;

	public List<Sale> findAll() {
		return repository.findAll();
	}

	public List<Sale> findByCustomer(Customer customer) {
		return repository.findByCustomer(customer);
	}

	public void save(Sale sale) {
		repository.save(sale);
	}
}
