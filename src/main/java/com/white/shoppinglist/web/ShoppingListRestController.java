package com.white.shoppinglist.web;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.white.shoppinglist.EntityMapper;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ShoppingListRepository;
import org.slf4j.LoggerFactory; //useful later

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ShoppingListRestController {
    
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private EntityMapper mapper;
    
    // Get all lists.
    // Get mapping, retrieves all shopping lists.
    @GetMapping("/shoppinglists")
    public ResponseEntity<List<ShoppingListDTO>> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = (List<ShoppingList>) shoppingListRepository.findAll();

        // Map shopping lists to DTOs
        List<ShoppingListDTO> shoppingListDTOs = shoppingLists
            .stream()
            .map(shoppingList -> mapper.toDto(shoppingList))
            .collect(Collectors.toList());

        return new ResponseEntity<>(shoppingListDTOs, HttpStatus.OK);
    }

    // Retrieves shopping list by id.
    @GetMapping("/shoppinglists/{id}")
    public ResponseEntity<ShoppingListDTO> getShoppinglistById(@PathVariable("id") Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);

        if (shoppingList.isPresent()) {
            return new ResponseEntity<>(mapper.toDto(shoppingList.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Post mapping, creates a new shopping list.
    @PostMapping("/shoppinglists")
    public ResponseEntity<ShoppingListDTO> createShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {
        // Validate input data
        if (shoppingListDTO.getName() == null || shoppingListDTO.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Create a new ShoppingList entity
        ShoppingList shoppingList = new ShoppingList(shoppingListDTO.getName());

        // Save the new shopping list
        ShoppingList createdShoppingList = shoppingListRepository.save(shoppingList);

        return new ResponseEntity<>(mapper.toDto(createdShoppingList), HttpStatus.CREATED);
    }

    // Deletes a shopping list and all its products.
    // JPA takes care of the products so we don't need to delete them explicitly.
    @DeleteMapping("/shoppinglists/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable("id") Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);

        if (shoppingList.isPresent()) {
            shoppingListRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
