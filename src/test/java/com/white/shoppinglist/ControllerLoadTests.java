package com.white.shoppinglist;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.white.shoppinglist.product.ProductController;
import com.white.shoppinglist.product.ProductRestController;
import com.white.shoppinglist.shoppinglist.ShoppingListRestController;

@SpringBootTest
public class ControllerLoadTests {
    private final ProductRestController productRestController;
    private final ShoppingListRestController shoppingListRestController;
    private final ProductController productController;

    @Autowired
    public ControllerLoadTests(
        ProductRestController productRestController,
        ShoppingListRestController shoppingListRestController,
        ProductController productController)
    {
        this.productRestController = productRestController;
        this.shoppingListRestController = shoppingListRestController;
        this.productController = productController;
    }

    @Test
    public void testProductRestControllerLoads() {
        assertNotNull(productRestController);
    }

    @Test
    public void testShoppingListRestControllerLoads() {
        assertNotNull(shoppingListRestController);
    }

    @Test
    public void testProductControllerLoads() {
        assertNotNull(productController);
    }
}
