package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Color;
import com.gutotech.everyone.repository.ColorRepository;

@Service
public class ColorService {
	@Autowired
	private ColorRepository repository;

	public List<Color> findAll() {
		return repository.findAllByOrderByName();
	}

	public Color findById(long id) {
		return repository.findById(id).orElse(null);
	}

	public void save(Color color) {
		repository.save(color);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
