package com.white.shoppinglist.product;

import org.springframework.data.repository.CrudRepository;

import com.white.shoppinglist.shoppinglist.ShoppingList;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    //testi luokkiin 
    List<Product> findByShoppingList(ShoppingList shoppingList);
    List<Product> findByName(String name);
    List<Product> findByShoppingListName(String name);
}
