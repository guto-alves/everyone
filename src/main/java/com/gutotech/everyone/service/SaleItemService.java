package com.gutotech.everyone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.SaleItem;
import com.gutotech.everyone.repository.SaleItemRepository;

@Service
public class SaleItemService {
	@Autowired
	private SaleItemRepository repository;

	public void save(SaleItem item) {
		repository.save(item);
	}
	
	public void saveAll(Iterable<SaleItem> items) {
		repository.saveAll(items);
	}

}
