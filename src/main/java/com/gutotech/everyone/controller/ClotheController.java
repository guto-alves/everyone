package com.gutotech.everyone.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.everyone.model.Clothe;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.service.BrandService;
import com.gutotech.everyone.service.CategoryService;
import com.gutotech.everyone.service.ClotheService;
import com.gutotech.everyone.service.ColorService;
import com.gutotech.everyone.service.CustomerService;

@Controller
@RequestMapping("clothes")
public class ClotheController {

	@Autowired
	private ClotheService clotheService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ColorService colorService;

	@Autowired
	private CustomerService customerService;

	@ModelAttribute("categories")
	public List<String> findAllCategories() {
		return categoryService.findAll().stream().map(category -> category.getName()).collect(Collectors.toList());
	}

	@ModelAttribute("colors")
	public List<String> findAllColors() {
		return colorService.findAll().stream().map(color -> color.getName()).collect(Collectors.toList());
	}

	@ModelAttribute("brands")
	public List<String> findAllBrands() {
		return brandService.findAll().stream().map(brand -> brand.getName()).collect(Collectors.toList());
	}

	@ModelAttribute("clothes")
	public List<Clothe> findAllClothes() {
		return clotheService.findAll();
	}

	@GetMapping
	public String initFindForm(Model model) {
		return "clothes/clothes";
	}

	@GetMapping("sort")
	public String sortClothesBy(@RequestParam(name = "color", required = false) List<String> colors,
			@RequestParam(name = "brand", required = false) List<String> brands,
			@RequestParam(name = "price", required = false, defaultValue = "0") int price, Model model) {
		// filter clothes
		List<Clothe> clothes = findAllClothes().stream()
				.filter(clothe -> colors == null || colors.contains(clothe.getColor().getName()))
				.filter(clothe -> brands == null || brands.contains(clothe.getBrand().getName()))
				.filter(clothe -> price == 0 || clothe.getPrice() < price).collect(Collectors.toList());

		// add matches clothes to the model
		model.addAttribute("clothes", clothes);

		model.addAttribute("filterColors", colors);
		model.addAttribute("filterBrands", brands);
		model.addAttribute("filterPrice", price);

		return "clothes/clothes";
	}

	@GetMapping("details/{id}")
	public String showClotheDetails(@PathVariable("id") long id, Model model) {
		Clothe clothe = clotheService.findById(id);

		model.addAttribute("isCart", false);

		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer customer = customerService.findByEmail(email);

			model.addAttribute("isCart", customer.getCart() != null ? customer.getCart().contains(clothe) : false);
		}

		model.addAttribute("clothe", clothe);

		return "clothes/clothe_details";
	}

	@GetMapping("new")
	public String initCreationForm(Model model) {
		model.addAttribute("colors", colorService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("clothe", new Clothe());
		return "clothes/clothe-form";
	}

	@PostMapping("new")
	public String proccessCreationForm(@Valid Clothe clothe, RedirectAttributes redirectAttributes,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(clothe);
			return "clothes/clothes-form";
		}

		clotheService.save(clothe);

		redirectAttributes.addFlashAttribute("message", "Clothing successfully added!");

		return "redirect:/clothes/new";
	}
}
