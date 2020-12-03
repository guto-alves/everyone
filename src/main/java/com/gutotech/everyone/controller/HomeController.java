package com.gutotech.everyone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/", "index", "home"})
	public String home() {
		return "index";
	}
}
