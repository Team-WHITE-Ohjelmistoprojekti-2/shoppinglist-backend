package com.white.shoppinglist.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    //testi luokkiin 
    List<Product> findByShoppingList(ShoppingList shoppingList);
    List<Product> findByName(String name);
    List<Product> findByShoppingListName(String name);
}
