package com.gutotech.everyone.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.service.CustomerService;

@Controller
public class LoginController {
	@Autowired
	private CustomerService service;

	@RequestMapping("login")
	public String initLoginForm() {
		return "login/login";
	}

	@RequestMapping("register")
	public String initRegistrationForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "login/register";
	}

	@PostMapping("register")
	public String processRegistrationForm(@Valid Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("customer", customer);
			return "login/register";
		}

		service.register(customer);

		return "redirect:/login";
	}
}
