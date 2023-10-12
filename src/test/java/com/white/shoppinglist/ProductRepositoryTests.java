package com.white.shoppinglist;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.white.shoppinglist.domain.ProductRepository;
import com.white.shoppinglist.domain.Product;

@SpringBootTest
public class ProductRepositoryTests {
    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTests(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    public void testFindAllProducts() {
        Product product = new Product();
        productRepository.save(product);
        List<Product> products = (List<Product>) productRepository.findAll();

        assertTrue(products.size() > 0);
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        Product savedProduct = productRepository.save(product);
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertTrue(foundProduct.get().getId() == savedProduct.getId());
    }
}
