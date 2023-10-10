package com.white.shoppinglist;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.white.shoppinglist.web.ProductController;
import com.white.shoppinglist.web.ProductRestController;
import com.white.shoppinglist.web.ShoppingListRestController;

@SpringBootTest
public class SmokeTest {
    private final ProductRestController productRestController;
    private final ShoppingListRestController shoppingListRestController;
    private final ProductController productController;

    @Autowired
    public SmokeTest(
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
