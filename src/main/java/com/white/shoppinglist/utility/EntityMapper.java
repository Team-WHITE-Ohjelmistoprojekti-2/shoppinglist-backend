package com.white.shoppinglist.utility;

import org.springframework.stereotype.Component;

import com.white.shoppinglist.product.Product;
import com.white.shoppinglist.shoppinglist.ShoppingList;
import com.white.shoppinglist.product.ProductDTO;
import com.white.shoppinglist.shoppinglist.ShoppingListDTO;

@Component
public class EntityMapper {
    // Map Product object to ProductDTO object.
    public ProductDTO toDto(Product product) {
        Long shoppinglistId = null;
        if (product.getShoppingList() != null) {
            shoppinglistId = product.getShoppingList().getId();
        }

        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDetails(),
            product.getPrice(),
            product.getQuantity(),
            shoppinglistId);
    }

    // Map Shoppinglist object to ShoppinglistDTO object.
    public ShoppingListDTO toDto(ShoppingList shoppingList) {
        return new ShoppingListDTO(
            shoppingList.getId(),
            shoppingList.getName(),
            shoppingList.getCreatedAt(),
            shoppingList.getUpdatedAt());
    }
}
