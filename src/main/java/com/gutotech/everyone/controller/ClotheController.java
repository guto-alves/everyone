package com.gutotech.everyone.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.gutotech.everyone.model.Review;
import com.gutotech.everyone.service.BrandService;
import com.gutotech.everyone.service.CategoryService;
import com.gutotech.everyone.service.ClotheService;
import com.gutotech.everyone.service.ColorService;
import com.gutotech.everyone.service.CustomerService;
import com.gutotech.everyone.service.ReviewService;

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

	@Autowired
	private ReviewService reviewService;

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

	@GetMapping("filter")
	public String sortClothesBy(@RequestParam(name = "gender", required = false) List<String> genders,
			@RequestParam(name = "category", required = false) Set<String> categories,
			@RequestParam(name = "color", required = false) List<String> colors,
			@RequestParam(name = "brand", required = false) List<String> brands,
			@RequestParam(name = "price", required = false, defaultValue = "0") int price, Model model) {

		List<Clothe> clothes = findAllClothes().stream()
				.filter(clothe -> genders == null || genders.contains(clothe.getCategory().getGender().getName()))
				.filter(clothe -> categories == null || categories.contains(clothe.getCategory().getName()))
				.filter(clothe -> colors == null || colors.contains(clothe.getColor().getName()))
				.filter(clothe -> brands == null || brands.contains(clothe.getBrand().getName()))
				.filter(clothe -> price == 0 || clothe.getPrice() <= price).collect(Collectors.toList());

		model.addAttribute("clothes", clothes);
		model.addAttribute("filterGenders", genders);
		model.addAttribute("filterCategories", categories);
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
		model.addAttribute("reviews", reviewService.findAllByClothe(clothe));

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

	@GetMapping("edit/{clotheId}")
	public String initUpdateClotheForm(@PathVariable("clotheId") long clotheId, Model model) {
		Clothe clothe = clotheService.findById(clotheId);

		model.addAttribute("colors", colorService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("clothe", clothe);
		return "clothes/clothe-form";
	}

	@PostMapping("edit/{clotheId}")
	public String proccessUpdateForm(@Valid Clothe clothe, RedirectAttributes redirectAttributes, BindingResult result,
			Model model) {
		model.addAttribute(clothe);

		if (result.hasErrors()) {
			return "clothes/clothes-form";
		}

		clotheService.save(clothe);

		redirectAttributes.addFlashAttribute("message", "Clothing successfully updated!");

		return "redirect:/clothes/edit/{clotheId}";
	}

	@PostMapping("review/{clotheId}")
	public ResponseEntity<Void> saveReview(@PathVariable("clotheId") long clotheId, @RequestParam("stars") int stars,
			@RequestParam("comment") String comment) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Customer customer = customerService.findByEmail(email);
		
		Clothe clothe = clotheService.findById(clotheId);
		
		Review review = new Review(stars, comment, new Date(), clothe, customer);
		System.out.println("TEST CUSTOMER: " + customer.getEmail());
		System.out.println("TEST CUSTOMER: " + review.getCustomer());
		
		reviewService.save(review);
		
		return ResponseEntity.ok().build();
	}
}
