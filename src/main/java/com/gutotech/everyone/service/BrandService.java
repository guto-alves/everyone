package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Brand;
import com.gutotech.everyone.repository.BrandRepository;

@Service
public class BrandService {
	@Autowired
	private BrandRepository repository;

	public List<Brand> findAll() {
		return repository.findAll();
	}

	public List<Brand> findByName(String name) {
		return repository.findByName(name);
	}

	public void save(Brand brand) {
		repository.save(brand);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public boolean brandExists(String name) {
		return findByName(name).size() > 1;
	}
}
