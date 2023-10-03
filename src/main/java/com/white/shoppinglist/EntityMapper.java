package com.white.shoppinglist;

import org.springframework.stereotype.Component;

import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.web.ProductDTO;

@Component
public class EntityMapper {
    // Map Product object to ProductDTO object.
    public ProductDTO toDto(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDetails(),
            product.getPrice(),
            product.getQuantity(),
            product.getShoppingList().getId());
    }
}
