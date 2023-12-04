package com.white.shoppinglist.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    // Configures CORS and sets allowed origins, methods, and headers.
	// Global CORS configuration can be modified here.
	@Bean
	public WebMvcConfigurer corsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
                    .allowedOrigins("*")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
					.allowCredentials(false);
			}
		};
	}
}
