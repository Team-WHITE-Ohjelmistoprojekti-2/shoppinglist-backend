package com.white.shoppinglist.web;

import java.util.List;
import java.util.Optional;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ProductRepository;
import com.white.shoppinglist.domain.ShoppingListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    // This gets all products if no query parameters were passed.
    // Gets products for a shoppinglist if shoppinglist id was passed in query parameter.
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(value = "shoppinglist", required = false) Long shoppingListId) {
        // shoppinglist id was passed in query parameter
        if (shoppingListId != null) {
            Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);

            // shopping list doesn't exist
            if (shoppingList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Product> products = productRepository.findByShoppingList(shoppingList.get());
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

        List<Product> products = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
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
