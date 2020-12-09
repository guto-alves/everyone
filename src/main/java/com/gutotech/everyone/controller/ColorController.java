package com.gutotech.everyone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.everyone.model.Color;
import com.gutotech.everyone.service.ColorService;

@Controller
@RequestMapping("colors")
public class ColorController {

	@Autowired
	private ColorService service;

	@ModelAttribute("colors")
	public List<Color> findAllColors() {
		return service.findAll();
	}

	@GetMapping
	public String initCreationForm(Model model) {
		model.addAttribute("color", new Color());
		return "colors/color-form";
	}

	@PostMapping
	public String processCreationForm(@Valid Color color, BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute(color);
			return "colors/color-form";
		}

		try {
			service.save(color);
		} catch (Exception e) {
			result.rejectValue("name", "duplicate", "already exists");
			return "colors/color-form";
		}

		redirectAttributes.addFlashAttribute("message", color.getName() + " was added!");

		return "redirect:/colors";
	}

	@PostMapping("remove/{color}")
	public String removeColor(@PathVariable("color") Long colorId) {
		service.deleteById(colorId);
		return "redirect:/colors";
	}

}
