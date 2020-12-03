package com.gutotech.everyone.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.everyone.error.InvalidOldPasswordException;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.service.CustomerService;
import com.gutotech.everyone.service.SaleService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;

	@Autowired
	private SaleService saleService;;
	
	@ModelAttribute("customer")
	public Customer getCustomer() {
		return service.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@GetMapping("customer/edit-profile")
	public String initUpdateProfileForm() {
		/*
		 * Customer customer =
		 * service.findByEmail(SecurityContextHolder.getContext().getAuthentication().
		 * getName());
		 * 
		 * model.addAttribute("customer", customer);
		 */

		return "customer/edit-profile";
	}

	@PostMapping("customer/edit-profile")
	public String processUpdateProfileForm(@Valid Customer customer, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(customer);
			return "customer/edit-profile";
		}

		service.save(customer);

		redirectAttributes.addFlashAttribute("message", "Your changes have been successfully saved.");

		return "redirect:/customer/edit-profile";
	}

	@GetMapping("customer/edit-account")
	public String showChangePasswordPage(Model model) {
		model.addAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());
		return "customer/edit-account";
	}

	@PostMapping("customer/edit-account")
	public ResponseEntity<String> processChangePasswordPage(@RequestParam("currentpassword") String password,
			@RequestParam("newpassword") String newPassword, Model model) {
		Customer customer = service.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		if (!service.checkIfValidOldPassword(customer, password)) {
			throw new InvalidOldPasswordException("Password was incorrect!");
		}

		service.changePassword(customer, newPassword);

		return ResponseEntity.ok("Password updated successfully.");
	}

	@GetMapping("customer/edit-credit-cards")
	public String initCreditCardsPage() {
		return "customer/edit-credit-cards";
	}

	@PostMapping("customer/edit-credit-cards")
	public String processCreditCardsPage(@RequestParam(name = "savecards", defaultValue = "false") boolean saveCards,
			@ModelAttribute("customer") Customer customer, RedirectAttributes attributes, Model model) {
		customer.setSaveCards(saveCards);
		service.save(customer);
		attributes.addFlashAttribute("success", true);
		return "redirect:/customer/edit-credit-cards";
	}

	@GetMapping("customer/purchase-history")
	public String showPurchaseHistory(Model model) {
		Customer customer = service.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("sales", saleService.findByCustomer(customer));
		return "customer/purchase-history";
	}

}
