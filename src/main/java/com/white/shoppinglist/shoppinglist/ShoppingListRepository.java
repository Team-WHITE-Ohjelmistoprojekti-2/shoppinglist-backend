package com.white.shoppinglist.shoppinglist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.white.shoppinglist.product.Product;


public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

    Optional<Product> findProductByIdAndId(Long productId, Long shoppingListId);
    List<ShoppingList> findByName(String name);
}
