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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.everyone.model.Category;
import com.gutotech.everyone.service.CategoryService;

@Controller
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@ModelAttribute("categories")
	public List<Category> findAllCategorys() {
		return service.findAll();
	}

	@ResponseBody
	@GetMapping("api")
	public List<Category> findAllByGender(@RequestParam(name = "gender", required = false) List<String> genders) {
		List<Category> categories = genders != null ? service.findAllByGenders(genders) : service.findAll();
		return categories;
	}

	@GetMapping
	public String initCreationForm(Model model) {
		model.addAttribute("category", new Category());
		return "categories/category-form";
	}

	@PostMapping
	public String processCreationForm(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(category);
			return "categories/category-form";
		}

		service.save(category);

		return "redirect:/categories";
	}
}
