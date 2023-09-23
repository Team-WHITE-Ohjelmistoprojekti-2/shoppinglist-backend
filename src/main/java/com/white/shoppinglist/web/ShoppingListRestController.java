package com.white.shoppinglist.web;
import java.util.List;
import java.util.Optional;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ProductRepository;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ShoppingListRepository;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ShoppingListRestController {
    
    @Autowired
    private ShoppingListRepository shoppinglistrepository;

    // Get all lists.
    @GetMapping("/shoppinglists")
    public List<ShoppingList> getAllShoppingLists() {
        List<ShoppingList> shoppinglists = (List<ShoppingList>) shoppinglistrepository.findAll();

        return shoppinglists;
    }
}
