package com.gutotech.everyone.config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gutotech.everyone.model.Brand;
import com.gutotech.everyone.model.Category;
import com.gutotech.everyone.model.Color;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.model.Gender;
import com.gutotech.everyone.model.Product;
import com.gutotech.everyone.repository.BrandRepository;
import com.gutotech.everyone.repository.CartItemRepository;
import com.gutotech.everyone.repository.CategoryRepository;
import com.gutotech.everyone.repository.ColorRepository;
import com.gutotech.everyone.repository.CreditCardRepository;
import com.gutotech.everyone.repository.CustomerRepository;
import com.gutotech.everyone.repository.ProductRepository;
import com.gutotech.everyone.repository.ReviewRepository;
import com.gutotech.everyone.repository.SaleItemRepository;
import com.gutotech.everyone.repository.SaleRepository;
import com.gutotech.everyone.service.CustomerService;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

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
		productRepository.deleteAll();
		colorRepository.deleteAll();
		categoryRepository.deleteAll();
		brandRepository.deleteAll();
		customerRepository.deleteAll();

		Color yellow = new Color("Yellow");
		Color blue = new Color("Blue");
		Color darkBlue = new Color("Dark Blue");
		Color white = new Color("White");
		Color gray = new Color("Gray");
		Color black = new Color("Black");
		Color green = new Color("Green");
		Color red = new Color("Red");
		Color redAndBeige = new Color("Red and Beige");
		Color pink = new Color("Light Jeans");
		Color orange = new Color("Orange");
		Color militaryGreen = new Color("Military Green");
		Color bronze = new Color("Bronze");
		colorRepository.saveAll(List.of(yellow, blue, darkBlue, white, gray, black, green, red, redAndBeige, pink,
				orange, militaryGreen, bronze));

		Brand brand1 = new Brand("Adidas");
		Brand brand2 = new Brand("Nike");
		Brand brand3 = new Brand("Calvin Klein");
		Brand brand4 = new Brand("Ecko");
		Brand brand5 = new Brand("Oakley");
		Brand brand6 = new Brand("Gonew");
		Brand brand7 = new Brand("Skate Eterno");
		Brand brand8 = new Brand("Let\'sGym");
		brandRepository.saveAll(List.of(brand1, brand2, brand3, brand4, brand5, brand6, brand7, brand8));

		Category category1 = new Category("Bermuda", Gender.MALE);
		Category category2 = new Category("Pants", Gender.MALE);
		Category category3 = new Category("Team shirts", Gender.MALE);
		Category category4 = new Category("Polo shirts", Gender.MALE);
		Category category5 = new Category("T-shirts", Gender.MALE);
		Category category7 = new Category("Hoodies", Gender.MALE);
		Category category8 = new Category("Plus Size", Gender.MALE);
		Category category9 = new Category("Shorts", Gender.MALE);
		Category category10 = new Category("Uniforms", Gender.MALE);

		Category category11 = new Category("Jeans/denim", Gender.FEMALE);
		Category category12 = new Category("Knitwear and Sweaters", Gender.FEMALE);
		Category category13 = new Category("Dresses", Gender.FEMALE);
		Category category14 = new Category("Tops", Gender.FEMALE);
		Category category15 = new Category("Jackets & Coats", Gender.FEMALE);
		Category category16 = new Category("Shirts", Gender.FEMALE);
		Category category17 = new Category("Shorts", Gender.FEMALE);

		categoryRepository.saveAll(List.of(category1, category2, category3, category4, category5, category7, category8,
				category9, category10, category11, category12, category13, category14, category15, category16,
				category17));

		// Customers
		Customer admin = new Customer("admin@everyone.com", "123", "Admin", "Admin", "Male", new Date());
		customerService.register(admin);
		admin.setRole("ROLE_ADMIN");
		customerService.save(admin);

		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 03, 27);
		Customer customer1 = new Customer("gustavo@gmail.com", "123", "Gustavo", "Alves", "Male", calendar.getTime());
		Customer customer2 = new Customer("beatris@gmail.com", "123", "Beatris", "Franklin", "Female",
				calendar.getTime());
		Customer customer3 = new Customer("murillo@gmail.com", "123", "Murillo", "Meira", "Male", calendar.getTime());

		customerService.register(customer1);
		customerService.register(customer2);
		customerService.register(customer3);

		// Clothes
		Product product1 = new Product("Camiseta Nike Sb Artist 3", "Camiseta Nike Sb Artist 3 Masculina", 99.99, 23,
				150,
				"https://static.netshoes.com.br/produtos/camiseta-nike-sb-artist-3-masculina/28/HZM-4335-028/HZM-4335-028_zoom1.jpg?ts=1606745715&ims=326x",
				category5, white, brand2);
		Product product2 = new Product("Camiseta Nike Dri-Fit Breathe Run Masculina",
				"Camiseta Nike Sb Artist 3 Masculina", 59.99, 33, 100,
				"https://static.netshoes.com.br/produtos/camiseta-nike-dri-fit-breathe-run-masculina/90/HZM-2977-290/HZM-2977-290_zoom1.jpg?ts=1605709397&ims=326x",
				category5, redAndBeige, brand2);
		Product product3 = new Product("Short Gonew Vibe", "Short Gonew Vibe Feminino", 39.99, 0, 50,
				"https://static.netshoes.com.br/produtos/short-gonew-vibe-feminino/18/C62-2791-018/C62-2791-018_zoom1.jpg?ts=1607003787&ims=326x",
				category17, pink, brand6);
		Product product4 = new Product("Calça Moletom Skate Eterno Clean",
				"Calça Moletom Skate Eterno Estampado em Silk Screen Medidas: Altura x Largura EP 97cm x 35cm P 99cm x 37cm M 101cm x 39cm G 103cm x 41cm GG 105cm x 43cm EGG 107cm x 45cm EEGG 109cm x 47cm",
				139.90, 0, 50,
				"https://static.netshoes.com.br/produtos/calca-moletom-skate-eterno-clean-masculina/06/PGX-0051-006/PGX-0051-006_zoom1.jpg?ts=1607006703&ims=326x",
				category2, black, brand7);
		Product product5 = new Product("Short Adidas Must Haves",
				"Leveza e conforto para você superar desafios com o Short Adidas Must Haves Masculino! O material macio fornece flexibilidade durante os movimentos, enquanto o cós em cordão permite um ajuste personalizado. Os bolsos laterais comportam pequenos objetos.",
				149.99, 0, 50,
				"https://static.netshoes.com.br/produtos/short-adidas-must-haves-masculino/06/NQQ-4666-006/NQQ-4666-006_zoom1.jpg?ts=1602781185&ims=326x",
				category9, black, brand1);
		Product product6 = new Product("Camiseta Ecko Pixo", "Camiseta Ecko Pixo Masculina", 29.99, 0, 50,
				"https://static.netshoes.com.br/produtos/camiseta-ecko-pixo-masculina/14/B25-1988-014/B25-1988-014_detalhe1.jpg?ts=1605641121?ims=326x",
				category5, white, brand4);
		Product product7 = new Product("Jaqueta Let'sGym Dry Essence",
				"Jaqueta em tecido Dry com certa transparência, de bom caimento, toque macio, assim como proporciona respirabilidade e transpirabilidade à peça! É extremamente confortável de usar, fácil de lavar e tem secagem rápida. Tem zíper, capuz e bolsos. Esta jaqueta combina com os mais diversos looks!. Modelagem Ajustada. Medidas: P(36-38) Costas: 38-40cm, Busto: 90-94cm, Cintura:65-71cm, Cintura Baixa: 72-78cm, Quadril: 96-99cm. M(39-41) Costas: 41-42cm, Busto: 95-98cm, Cintura: 72-78cm, Cintura Baixa: 77-85cm, Quadril: 100-106. G(42-44) Costas: 43-44cm, Busto: 99-102cm, Cintura: 79-85cm, Cintura Baixa: 89-95cm, Quadril: 107-112cm. Medidas padrão para mulher de 1,70m de altura.",
				154.36, 0, 50,
				"https://static.netshoes.com.br/produtos/jaqueta-letsgym-dry-essence-feminina/06/EDG-0266-006/EDG-0266-006_zoom1.jpg?ts=1607344286&ims=326x",
				category15, black, brand8);
		Product product8 = new Product("Blusão Let'sGym Sky Estonado",
				"Blusão confeccionado em Moletinho, proporcionando liberdade de movimento, maior conforto fisiológico, permitindo a troca de calor com o ambiente. Por ser fabricado em 100% Algodão, absorve o suor e facilita a transpiração e no frio atua como isolante térmico. Criado com a técnica de estonagem, em que cada peça é única e tem efeitos singulares, pois passa por lavagens especiais para criar um aspecto mais desbotado, mas com um toque mais macio e maleável. Possui uma barra alta e justinha em ribana, capuz e cordinha de ajuste. Modelagem Soltinha. Medidas: P(36-38) Costas: 38-40cm, Busto: 90-94cm, Cintura:65-71cm, Cintura Baixa: 72-78cm, Quadril: 96-99cm. M(39-41) Costas: 41-42cm, Busto: 95-98cm, Cintura: 72-78cm, Cintura Baixa: 77-85cm, Quadril: 100-106. G(42-44) Costas: 43-44cm, Busto: 99-102cm, Cintura: 79-85cm, Cintura Baixa: 89-95cm, Quadril: 107-112cm. Medidas padrão para mulher de 1,70m de altura.",
				219.90, 0, 50,
				"https://static.netshoes.com.br/produtos/blusao-letsgym-sky-estonado-feminina/00/EDG-0232-800/EDG-0232-800_zoom1.jpg?ts=1607343496&ims=326x",
				category15, bronze, brand8);
		Product product9 = new Product("Camiseta Nike Legend 2.0 Ss",
				"Eleve sua performance com o conforto e a leveza da Camiseta Nike Legend 2.0. A peça oferece tecnologia Nike Dri-Fit para você se manter em movimento sem se preocupar com incômodos.",
				69.99, 30, 50,
				"https://static.netshoes.com.br/produtos/camiseta-nike-legend-20-ss-masculina/82/D12-2415-982/D12-2415-982_zoom1.jpg?ts=1607092845&ims=326x",
				category5, bronze, brand2);

		productRepository.saveAll(
				List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9));
	}

}
