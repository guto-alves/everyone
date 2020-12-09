package com.gutotech.everyone.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.everyone.model.Cart;
import com.gutotech.everyone.model.CartItem;
import com.gutotech.everyone.model.Clothe;
import com.gutotech.everyone.model.CreditCard;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.model.PaymentFormDto;
import com.gutotech.everyone.model.Sale;
import com.gutotech.everyone.model.SaleItem;
import com.gutotech.everyone.service.CartItemService;
import com.gutotech.everyone.service.ClotheService;
import com.gutotech.everyone.service.CreditCardService;
import com.gutotech.everyone.service.CustomerService;
import com.gutotech.everyone.service.SaleItemService;
import com.gutotech.everyone.service.SaleService;

@Controller
public class CartController {

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

	@Autowired
	private CreditCardService creditCardService;

	@ModelAttribute("cart")
	public Cart getCart() {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return customer.getCart();
	}

	@GetMapping("cart")
	public String showCart() {
		return "carts/cart";
	}

	@PostMapping("cart/add/{clotheId}")
	public String addToCart(@PathVariable("clotheId") long clotheId, HttpServletRequest request) {
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
	public String remove(@PathVariable("clotheId") Long clotheId, @ModelAttribute("cart") Cart cart, Model model) {
		CartItem cartItem = cart.getItems()
				.stream()
				.filter(item -> item.getClothe().getId() == clotheId)
				.findFirst()
				.get();

		cartItemService.delete(cartItem);

		return "redirect:/cart/";
	}

	@ResponseBody
	@PostMapping("cart/update")
	public Map<String, String> updateQuantity(@RequestParam("clotheid") long clotheId,
			@RequestParam("quantity") int newQuantity, @ModelAttribute("cart") Cart cart, Model model) {

		CartItem item = cart.getItems()
				.stream()
				.filter(i -> i.getClothe().getId() == clotheId)
				.findFirst()
				.get();

		item.setQuantity(newQuantity);
		cartItemService.save(item);

		cart.getItems().remove(item);
		cart.getItems().add(item);

		double total = cart.getItems()
				.stream()
				.mapToDouble(item1 -> item1.getQuantity() * item1.getClothe().getPrice())
				.sum();

		double newItemPrice = item.getQuantity() * item.getClothe().getPrice();

		return Map.of("total", String.format("%.2f", total), "newItemPrice", String.format("%.2f", newItemPrice));
	}

	@GetMapping("cart/checkout")
	public String showCheckoutPage(Model model) {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		model.addAttribute("cards", creditCardService.findAllByCustomer(customer));
		model.addAttribute("form", new PaymentFormDto());
		model.addAttribute("newCard", new CreditCard());

		return "/carts/checkout";
	}

	@PostMapping("cart/checkout")
	public String processCheckout(Cart cart, PaymentFormDto form, CreditCard newCard, RedirectAttributes attributes,
			Model model) {
		Customer customer = customerService
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		CreditCard paymentCard = null;

		if (form.getMethod().equals("card")) {
			if (form.getSelectedCard().getNumber() != null) {
				paymentCard = form.getSelectedCard();
			} else {
				if (form.isRememberCard()) {
					newCard.setCustomer(customer);
					creditCardService.save(newCard);
				}

				paymentCard = newCard;
			}
		}

		Sale sale = new Sale(new Date(), "Paid", customer);

		if (paymentCard != null) {
			sale.setCreditCard(paymentCard);
		}

		saleService.save(sale);

		List<SaleItem> saleItems = cart.getItems().stream()
				.map(cartItem -> new SaleItem(sale, cartItem.getClothe(), cartItem.getQuantity(),
						cartItem.getClothe().getPrice(), cartItem.getClothe().getDiscount()))
				.collect(Collectors.toList());

		saleItemService.saveAll(saleItems);

		cartItemService.deleteAll(cart.getItems());

		attributes.addFlashAttribute("message",
				"Purchase successful. To see more details, visit the Purchase History.");

		return "redirect:/";
	}
}
