package com.gutotech.everyone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gutotech.everyone.model.Category;
import com.gutotech.everyone.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService service;

	@ModelAttribute("categories")
	public List<Category> findAllCategorys() {
		return service.findAll();
	}

	@GetMapping("/category/new")
	public String initCreationForm(Model model) {
		model.addAttribute("category", new Category());
		return "categories/category-form";
	}

	@PostMapping("/category/new")
	public String processCreationForm(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(category);
			return "categories/category-form";
		}

		service.save(category);

		return "redirect:/category/new";
	}
}
