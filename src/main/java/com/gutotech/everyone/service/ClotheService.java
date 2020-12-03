package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Clothe;
import com.gutotech.everyone.repository.ClotheRepository;

@Service
public class ClotheService {
	@Autowired
	private ClotheRepository repository;

	public List<Clothe> findAll() {
		return repository.findAll();
	}

	public Clothe findById(long id) {
		return repository.findById(id).get();
	}

	public void save(Clothe clothe) {
		repository.save(clothe);
	}

}
