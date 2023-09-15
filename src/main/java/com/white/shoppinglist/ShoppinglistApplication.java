package com.white.shoppinglist;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ProductRepository;

@SpringBootApplication
public class ShoppinglistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);
	}

	@Bean
	public CommandLineRunner demodata(ProductRepository productRepository) {
		return (args) -> {

			// Products
			Product product1 = new Product("Peruna", "kova peruna", 0.79, 1);
			productRepository.save(product1);
			Product product2 = new Product("maitojauhe", "Tarvitsen", 2.79, 4);
			productRepository.save(product2);
			Product product3 = new Product("Ketsuppi", "Litran purkkeja", 4.29, 6);
			productRepository.save(product3);

		};
	}

	@Bean

	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOriginPatterns(List.of("http://localhost:517*"));

		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);

		return source;

	}
}
