package com.gutotech.everyone.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Category;
import com.gutotech.everyone.model.Gender;
import com.gutotech.everyone.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAllByOrderByName();
	}

	public List<Category> findAllByGenders(List<String> gendersString) {
		List<Gender> genders = gendersString.stream().map(string -> Gender.valueOf(string.toUpperCase()))
				.collect(Collectors.toList());
		List<Category> categories = repository.findByGenderIn(genders);
		categories.sort(Comparator.comparing(Category::getName));
		return categories;
	}

	public void save(Category category) {
		repository.save(category);
	}
}
