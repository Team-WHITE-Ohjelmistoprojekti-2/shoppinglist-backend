package com.white.shoppinglist.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.white.shoppinglist.EntityMapper;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ProductRepository;
import com.white.shoppinglist.domain.ShoppingListRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private EntityMapper mapper;

    // This gets all products if no query parameters were passed.
    // Gets products for a shoppinglist if shoppinglist id was passed in query parameter.
    @GetMapping("/products")
    @Operation(summary = "Get products")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful response",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
    public ResponseEntity<List<ProductDTO>> getProducts(
            @RequestParam(value = "shoppinglist", required = false) Long shoppingListId) {
        // shoppinglist id was passed in query parameter
        if (shoppingListId != null) {
            Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);

            // shopping list doesn't exist
            if (shoppingList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<ProductDTO> products = productRepository
                .findByShoppingList(shoppingList.get())
                .stream()
                .map(product -> mapper.toDto(product))
                .collect(Collectors.toList());

            return new ResponseEntity<>(products, HttpStatus.OK);
        }

        List<Product> products = (List<Product>) productRepository.findAll();
        List<ProductDTO> productDTOs = products
            .stream()
            .map(product -> mapper.toDto(product))
            .collect(Collectors.toList());

        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    // Get product by id.
    @GetMapping("/products/{id}")
    @Operation(summary = "Get a product by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful response",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return new ResponseEntity<>(mapper.toDto(product.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Post mapping, creates new product.
    // Uses data transfer object to transfer the shoppinglist id of product.
    @PostMapping("/products")
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successful response",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        // Product must belong to shoppinglist
        Long shoppinglistId = productDTO.getShoppinglistId();
        if (shoppinglistId == null) {
            return new ResponseEntity<>("shoppinglistId cannot be null", HttpStatus.BAD_REQUEST);
        }

        ShoppingList shoppingList = shoppingListRepository
            .findById(shoppinglistId)
            .orElse(null);
        if (shoppingList == null) {
            return new ResponseEntity<>("Shoppinglist doesn't exist", HttpStatus.BAD_REQUEST);
        }

        Product product = new Product(
            productDTO.getName(),
            productDTO.getDetails(),
            productDTO.getPrice(),
            productDTO.getQuantity(),
            shoppingList);

        Product createdProduct = productRepository.save(product);
        return new ResponseEntity<>(mapper.toDto(createdProduct), HttpStatus.CREATED);
    }

    // Put mapping, updates existing product.
    @PutMapping("/products/{id}")
    @Operation(summary = "Update an existing product")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successful response"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDTO newProduct, @PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setPrice(newProduct.getPrice());
        product.setDetails(newProduct.getDetails());
        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete mapping, deletes product by id returns errorcode if product is not
    // found.
    @DeleteMapping("/product/{id}")
    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successful response"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
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
