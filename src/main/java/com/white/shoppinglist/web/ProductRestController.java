package com.white.shoppinglist.web;

import java.util.List;
import java.util.Optional;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ProductRepository;
import com.white.shoppinglist.domain.ShoppingListRepository;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api")
public class ProductRestController {

    // private static final Logger log =
    // LoggerFactory.getLogger(ProductRestController.class);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    // Get all products.
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();

        return products;
    }

    // Get product by id.
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Gets all products for a shopping list by shopping list id
    @GetMapping("/products/list/{id}")
    public ResponseEntity<List<Product>> getShoppinglistProducts(@PathVariable("id") Long shoppingListId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);

        // if shopping list doesn't exist
        if (shoppingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Product> products = productRepository.findByShoppingList(shoppingList.get());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // post mapping, creates new product.
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // put mapping
    @PutMapping("/products/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setQuantity(newProduct.getQuantity());
                    product.setPrice(newProduct.getPrice());
                    product.setDetails(newProduct.getDetails());
                    return productRepository.save(product);
                }).orElseThrow();
    }

    // delete mapping, deletes product by id returns errorcode if product is not
    // found.
    @DeleteMapping("/product/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
