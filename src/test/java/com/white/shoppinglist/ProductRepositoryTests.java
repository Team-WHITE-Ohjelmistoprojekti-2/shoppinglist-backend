package com.white.shoppinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.white.shoppinglist.product.ProductRepository;
import com.white.shoppinglist.product.Product;

@SpringBootTest
public class ProductRepositoryTests {
    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTests(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    public void testFindAllProducts() {
        productRepository.save(new Product());
        List<Product> products = (List<Product>) productRepository.findAll();

        assertTrue(products.size() > 0);
    }

    @Test
    public void testFindProductById() {
        Product savedProduct = productRepository.save(new Product());
        Optional<Product> product = productRepository.findById(savedProduct.getId());

        assertTrue(product.isPresent());
        assertTrue(product.get().getId() == savedProduct.getId());
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        Long productId = product.getId();
        product.setName("test product");
        Product savedProduct = productRepository.save(product);

        assertTrue(savedProduct.getId() > 0);
        assertNotEquals(savedProduct.getId(), productId);
        assertEquals(savedProduct.getName(), product.getName());
        assertTrue(productRepository.findById(savedProduct.getId()).isPresent());
    }

    @Test
    public void testDeleteProduct() {
        Product savedProduct = productRepository.save(new Product());
        productRepository.deleteById(savedProduct.getId());
        Optional<Product> product = productRepository.findById(savedProduct.getId());

        assertTrue(product.isEmpty());
    }

    @Test
    public void testUpdateProduct() {
        Product savedProduct = productRepository.save(new Product());
        String savedProductName = savedProduct.getName();
        savedProduct.setName("updated");
        Product updatedProduct = productRepository.save(savedProduct);

        assertNotEquals(updatedProduct.getName(), savedProductName);
    }
}
