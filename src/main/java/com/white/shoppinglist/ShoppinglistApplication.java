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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ShoppingListRepository;
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
			ShoppingListRepository shoppinglistRepository) {
		return (args) -> {
			long existingShoppingListCount = shoppinglistRepository.count();

			if (existingShoppingListCount == 0) {
				// Luodaan demodata vain, jos tietokannassa ei ole vielä ShoppingListeja
				ShoppingList shoppingList = new ShoppingList("testi lista");
				shoppinglistRepository.save(shoppingList);

				ShoppingList shoppingList2 = new ShoppingList("testi 3214124124");
				shoppinglistRepository.save(shoppingList2);

				Product product1 = new Product("Peruna", "kova peruna", 0.79, 1, shoppingList);
				productRepository.save(product1);
				Product product2 = new Product("maitojauhe", "Tarvitsen", 2.79, 4, shoppingList);
				productRepository.save(product2);
				Product product3 = new Product("Ketsuppi", "Litran purkkeja", 4.29, 6, shoppingList);
				productRepository.save(product3);
				Product product4 = new Product("Leipä", "350g", 3.25, 6, shoppingList2);
				productRepository.save(product4);
			} else {
				// Tietokannassa on jo dataa, joten ei lisätä demodataa
				System.out.println("Tietokannassa on jo dataa, joten demodataa ei lisätä.");
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
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:5173") // frontendin osoite
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("Content-Type")
						.allowCredentials(false);
			}
		};
	}
}
