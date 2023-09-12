package com.white.shoppinglist.web;

import java.util.List;
import java.util.ArrayList;
import com.white.shoppinglist.domain.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProductRestController {
    
    // Get all products.
    // TODO: use product repository to get from database
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return new ArrayList<Product>();
    }

    // Get product by id.
    // TODO: use product repository to get from database
    @GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id") long productId) {
		return new Product();
	}
}
