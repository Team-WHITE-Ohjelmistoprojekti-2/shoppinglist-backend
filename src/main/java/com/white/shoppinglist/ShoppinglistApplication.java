package com.white.shoppinglist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ShoppingListRepository;
import com.white.shoppinglist.appuser.AppUser;
import com.white.shoppinglist.appuser.AppUserRepository;
import com.white.shoppinglist.appuser.AppUserRole;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ProductRepository;

@SpringBootApplication
public class ShoppinglistApplication {

	private static final Logger logger = LoggerFactory.getLogger(ShoppinglistApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);
	}

	@Bean
	public CommandLineRunner demodata(ProductRepository productRepository,
			ShoppingListRepository shoppinglistRepository, AppUserRepository appUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		return (args) -> {
			long existingShoppingListCount = shoppinglistRepository.count();

			if (existingShoppingListCount == 0) {
				// Create demo data only if there are no Shopping Lists in the database yet
				ShoppingList shoppingList = new ShoppingList("Jukan lista");
				shoppinglistRepository.save(shoppingList);

				ShoppingList shoppingList2 = new ShoppingList("Toinen lista");
				shoppinglistRepository.save(shoppingList2);

				Product product1 = new Product("Creality Ender-3 v2 - 220*220*250mm",
						"https://www.3dkauppa.com/13314-creality-ender-3-v2-220-220-250mm.html#attr=3300", 299.00, 1,
						shoppingList);
				productRepository.save(product1);
				Product product2 = new Product("PrimaSelect CARBON - 1.75mm - 500 g - Army Green",
						"https://www.3dkauppa.com/13124-primaselect-carbon-1-75mm-500-g-army-green.html?category=263&ppg=12#attr=",
						39.90, 1, shoppingList);
				productRepository.save(product2);
				Product product3 = new Product("Ketsuppi", "Litran purkkeja", 4.29, 6, shoppingList);
				productRepository.save(product3);

				Product product4 = new Product("Peruna", "kova peruna", 0.79, 1, shoppingList);
				productRepository.save(product1);
				Product product5 = new Product("Maitohauhe", "ei arlaa", 2.79, 4, shoppingList);
				productRepository.save(product2);
				Product product6 = new Product("Sinappi", "Litran purkkeja", 4.29, 6, shoppingList);
				productRepository.save(product3);

				Product product10 = new Product("Leipä", "350g", 3.25, 6, shoppingList2);
				productRepository.save(product10);
			} else {
				// There is already data in the database, so do not add demo data
				System.out.println("There is already data in the database, so demo data will not be added.");
			}

			// Create test user to test login
			if (appUserRepository.count() == 0) {
				AppUser appUser = new AppUser("testuser", "testuser1234!", "", AppUserRole.USER);
				String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
				appUser.setPassword(encodedPassword);

				appUserRepository.save(appUser);
			}
		};
	}

	// Configures CORS and sets allowed origins, methods, and headers.
	// Global CORS configuration can be modified here.
	@Bean
	public WebMvcConfigurer corsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:5173") // frontendin osoite
						.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("Content-Type")
						.allowCredentials(false);
			}
		};
	}
}
