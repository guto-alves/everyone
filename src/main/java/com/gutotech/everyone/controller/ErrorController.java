package com.gutotech.everyone.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ErrorController {

//	@ExceptionHandler(Throwable.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public String exception(final Throwable throwable, final Model model) {
//		String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
//		model.addAttribute("errorMessage", errorMessage);
//		return "error";
//	}

}