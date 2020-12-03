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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.everyone.model.Brand;
import com.gutotech.everyone.service.BrandService;

@Controller
public class BrandController {

	@Autowired
	private BrandService service;

	@ModelAttribute("brands")
	public List<Brand> findAllBrands() {
		return service.findAll();
	}

	@GetMapping("/brand/new")
	public String initCreationForm(Model model) {
		model.addAttribute("brand", new Brand());
		return "brands/brand-form";
	}

	@PostMapping("/brand/new")
	public String processCreationForm(@Valid Brand brand, BindingResult result, RedirectAttributes attributes,
			Model model) {
		if (result.hasErrors() || service.brandExists(brand.getName())) {
			model.addAttribute(brand);
			return "brands/brand-form";
		}

		service.save(brand);

		attributes.addFlashAttribute("message", brand.getName() + " was added!");

		return "redirect:/brand/new";
	}

	@PostMapping("/brand/remove")
	public String remove(@RequestParam("brandId") Long brandId) {
		service.deleteById(brandId);
		return "redirect:/brand/new";
	}
}
