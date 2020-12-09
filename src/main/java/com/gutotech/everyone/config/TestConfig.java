package com.gutotech.everyone.config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gutotech.everyone.model.Brand;
import com.gutotech.everyone.model.Category;
import com.gutotech.everyone.model.Clothe;
import com.gutotech.everyone.model.Color;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.model.Gender;
import com.gutotech.everyone.repository.BrandRepository;
import com.gutotech.everyone.repository.CartItemRepository;
import com.gutotech.everyone.repository.CategoryRepository;
import com.gutotech.everyone.repository.ClotheRepository;
import com.gutotech.everyone.repository.ColorRepository;
import com.gutotech.everyone.repository.CreditCardRepository;
import com.gutotech.everyone.repository.CustomerRepository;
import com.gutotech.everyone.repository.ReviewRepository;
import com.gutotech.everyone.repository.SaleItemRepository;
import com.gutotech.everyone.repository.SaleRepository;
import com.gutotech.everyone.service.CustomerService;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClotheRepository clotheRepository;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private SaleItemRepository saleItemRepository;

	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public void run(String... args) throws Exception {
		reviewRepository.deleteAll();
		creditCardRepository.deleteAll();
		saleItemRepository.deleteAll();
		saleRepository.deleteAll();
		cartItemRepository.deleteAll();
		clotheRepository.deleteAll();
		colorRepository.deleteAll();
		categoryRepository.deleteAll();
		brandRepository.deleteAll();
		customerRepository.deleteAll();

		Color yellow = new Color("Yellow");
		Color blue = new Color("Blue");
		Color white = new Color("White");
		Color gray = new Color("Gray");
		Color black = new Color("Black");
		Color green = new Color("Green");
		Color red = new Color("Red");
		Color redAndBeige = new Color("Red and Beige");
		Color pink = new Color("Pink");
		colorRepository.saveAll(List.of(yellow, blue, white, gray, black, green, red, redAndBeige, pink));

		Brand brand1 = new Brand("Adidas");
		Brand brand2 = new Brand("Nike");
		Brand brand3 = new Brand("Calvin Klein");
		Brand brand4 = new Brand("Ecko");
		Brand brand5 = new Brand("Oakley");
		Brand brand6 = new Brand("Gonew");
		brandRepository.saveAll(List.of(brand1, brand2, brand3, brand4, brand5, brand6));

		Category category1 = new Category("Bermuda", Gender.MALE);
		Category category2 = new Category("Pants", Gender.MALE);
		Category category3 = new Category("Team shirts", Gender.MALE);
		Category category4 = new Category("Polo shirts", Gender.MALE);
		Category category5 = new Category("Tshirts", Gender.MALE);
		Category category6 = new Category("Jackets & Coats", Gender.MALE);
		Category category7 = new Category("Hoodies", Gender.MALE);
		Category category8 = new Category("Plus Size", Gender.MALE);
		Category category9 = new Category("Shorts", Gender.MALE);
		Category category10 = new Category("Uniforms", Gender.MALE);

		Category category11 = new Category("Jeans pants", Gender.FEMALE);
		Category category12 = new Category("Knitwear and Sweaters", Gender.FEMALE);
		Category category13 = new Category("Dresses", Gender.FEMALE);
		Category category14 = new Category("Tops", Gender.FEMALE);
		Category category15 = new Category("Coats", Gender.FEMALE);
		Category category16 = new Category("Shirts", Gender.FEMALE);
		Category category17 = new Category("Shorts", Gender.FEMALE);

		categoryRepository.saveAll(List.of(category1, category2, category3, category4, category5, category6, category7,
				category8, category9, category10, category11, category12, category13, category14, category15,
				category16, category17));

		// Customers
		Customer admin = new Customer("admin@everyone.com", "123", "Admin", "Admin", "Male", new Date());
		customerService.register(admin);
		admin.setRole("ROLE_ADMIN");
		customerService.save(admin);

		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 03, 27);
		Customer customer1 = new Customer("gustavo@gmail.com", "123", "Gustavo", "Alves", "Male", calendar.getTime());
		Customer customer2 = new Customer("beatris@gmail.com", "123", "Beatris", "Silva", "Female", calendar.getTime());
		Customer customer3 = new Customer("murillo@gmail.com", "123", "Murillo", "Meira", "Male", calendar.getTime());

		customerService.register(customer1);
		customerService.register(customer2);
		customerService.register(customer3);

		// Clothes
		Clothe clothe1 = new Clothe("Camiseta Nike Sb Artist 3", "Camiseta Nike Sb Artist 3 Masculina", 99.99, 23, 150,
				"https://static.netshoes.com.br/produtos/camiseta-nike-sb-artist-3-masculina/28/HZM-4335-028/HZM-4335-028_zoom1.jpg?ts=1606745715&ims=326x",
				category5, white, brand2);
		Clothe clothe2 = new Clothe("Camiseta Nike Dri-Fit Breathe Run Masculina",
				"Camiseta Nike Sb Artist 3 Masculina", 59.99, 33, 100,
				"https://static.netshoes.com.br/produtos/camiseta-nike-dri-fit-breathe-run-masculina/90/HZM-2977-290/HZM-2977-290_zoom1.jpg?ts=1605709397&ims=326x",
				category5, redAndBeige, brand2);
		Clothe clothe3 = new Clothe("Short Gonew Vibe", "Short Gonew Vibe Feminino", 39.99, 0, 50,
				"https://static.netshoes.com.br/produtos/short-gonew-vibe-feminino/18/C62-2791-018/C62-2791-018_zoom1.jpg?ts=1607003787&ims=326x",
				category17, pink, brand6);

		clotheRepository.saveAll(List.of(clothe1, clothe2, clothe3));

	}

}
