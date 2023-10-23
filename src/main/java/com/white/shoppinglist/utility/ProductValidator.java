package com.white.shoppinglist.utility;

import org.springframework.stereotype.Component;
import com.white.shoppinglist.domain.Product;

@Component
public class ProductValidator {
    // Ensures product fields don't have invalid values.
    public void ensureValidValues(Product product) {
        if (product.getQuantity() < 1) {
            product.setQuantity(1);
        }

        if (product.getPrice() != null) {
            if (product.getPrice() < 0.0) {
                product.setPrice(0.0);
            }
        }
    }
}
