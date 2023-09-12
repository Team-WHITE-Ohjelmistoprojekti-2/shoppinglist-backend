package com.white.shoppinglist.web;

import java.util.List;
import java.util.Optional;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;
    
    // Get all products.
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>)productRepository.findAll();

        return products;
    }

    // Get product by id.
	@GetMapping("/products/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		HttpStatusCode status = HttpStatus.NOT_FOUND;
		
		if (product.isPresent()) {
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<>(product, status);
	}
}
