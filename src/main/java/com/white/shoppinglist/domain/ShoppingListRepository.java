package com.white.shoppinglist.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {
    Optional<Product> findProductByIdAndId(Long productId, Long shoppingListId);
}
