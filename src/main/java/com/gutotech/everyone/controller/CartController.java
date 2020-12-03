package com.gutotech.everyone.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.everyone.model.Cart;
import com.gutotech.everyone.model.CartItem;
import com.gutotech.everyone.model.Clothe;
import com.gutotech.everyone.model.CreditCard;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.model.Sale;
import com.gutotech.everyone.model.SaleItem;
import com.gutotech.everyone.service.CartItemService;
import com.gutotech.everyone.service.CartService;
import com.gutotech.everyone.service.ClotheService;
import com.gutotech.everyone.service.CustomerService;
import com.gutotech.everyone.service.SaleItemService;
import com.gutotech.everyone.service.SaleService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ClotheService clotheService;

	@Autowired
	private SaleService saleService;

	@Autowired
	private SaleItemService saleItemService;

	@ModelAttribute("cart")
	public Cart getCart() {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return customer.getCart();
	}

	@GetMapping("cart")
	public String showCart(Model model) {
//		model.addAttribute("customer", attributeValue)
		return "carts/cart";
	}

	@PostMapping("cart/add/{clotheId}")
	public String addToCart(@PathVariable("clotheId") long clotheId, HttpServletRequest request) {
		System.out.println("TAG: " + clotheId);
		
		Clothe clothe = clotheService.findById(clotheId);

		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer customer = customerService.findByEmail(email);

			CartItem item = new CartItem(customer.getCart(), clothe, 1);
			
			cartItemService.save(item);
		} else {
			String referrer = request.getHeader("Referer");
			request.getSession().setAttribute("url_prior_login", referrer);
			return "login/login";
		}

		return "redirect:/clothes/details/" + clotheId;
	}

	@PostMapping("cart/remove/{clotheId}")
	public String remove(@PathVariable("clotheId") Long clotheId, Model model) {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		customer.getCart().getItems().removeIf(item -> item.getClothe().getId() == clotheId);
		
		cartService.save(customer.getCart());

		customerService.save(customer);

		return "redirect:/cart/";
	}

	@ResponseBody
	@PostMapping("cart/update-quantity")
	public String updateQuantity(@RequestParam("clotheid") long clotheId, @RequestParam("newquantity") int quantity) {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		CartItem item = customer.getCart().getItems().stream().filter(i -> i.getClothe().getId() == clotheId)
				.findFirst().get();

		item.setQuantity(quantity);

		customer.getCart().getItems().remove(item);
		customer.getCart().getItems().add(item);

		customerService.save(customer);

		double newItemPrice = item.getQuantity() * item.getClothe().getPrice();

		double total = customer.getCart().getItems().stream()
				.mapToDouble(item1 -> item1.getQuantity() * item1.getClothe().getPrice()).sum();

		return String.format("%.2f%n%.2f", newItemPrice, total);
	}

	@GetMapping("cart/checkout")
	public String showCheckoutPage(Model model) {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("cards", customer.getCards());
		model.addAttribute("creditCardt", new CreditCard());
		return "/carts/checkout";
	}

	@PostMapping("cart/checkout")
	public String processCheckout(Cart cart, CreditCard creditCard, Model model) {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		Sale sale = new Sale(new Date(), "Paid", customer);
		saleService.save(sale);

		List<SaleItem> saleItems = cart.getItems().stream()
				.map(cartItem -> new SaleItem(sale, cartItem.getClothe(), cartItem.getQuantity(),
						cartItem.getClothe().getPrice(), cartItem.getClothe().getDiscount()))
				.collect(Collectors.toList());

		saleItemService.saveAll(saleItems);

		return "redirect:/";
	}
}
