package com.white.shoppinglist.web;
import java.util.List;
import java.util.stream.Collectors;


import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ShoppingListRepository;
import org.slf4j.LoggerFactory; //useful later
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; //coming soon
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ShoppingListRestController {
    
    @Autowired
    private ShoppingListRepository shoppinglistrepository;
    
    // Get all lists.
    // Get mapping, retrieves all shopping lists.
    @GetMapping("/shoppinglists")
    public ResponseEntity<List<ShoppingListDTO>> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = (List<ShoppingList>) shoppinglistrepository.findAll();

        // Map shopping lists to DTOs
        List<ShoppingListDTO> shoppingListDTOs = shoppingLists.stream()
            .map(shoppingList -> {
                ShoppingListDTO dto = new ShoppingListDTO();
                dto.setId(shoppingList.getId());
                dto.setName(shoppingList.getName());
                // Set other properties as needed
                return dto;
            })
            .collect(Collectors.toList());

        return new ResponseEntity<>(shoppingListDTOs, HttpStatus.OK);
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
        ShoppingList createdShoppingList = shoppinglistrepository.save(shoppingList);

        // Create a DTO for the response
        ShoppingListDTO createdShoppingListDTO = new ShoppingListDTO(
            createdShoppingList.getId(),
            createdShoppingList.getName());

        return new ResponseEntity<>(createdShoppingListDTO, HttpStatus.CREATED);
    }

}
