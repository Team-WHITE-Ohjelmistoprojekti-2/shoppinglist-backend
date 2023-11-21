package com.white.shoppinglist.shoppinglist;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.white.shoppinglist.utility.EntityMapper;
import com.white.shoppinglist.shoppinglist.ShoppingList;
import com.white.shoppinglist.shoppinglist.ShoppingListRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ShoppingListRestController {
    
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private EntityMapper mapper;
    
    // Retrieves all shopping lists.
    @GetMapping("/shoppinglists")
    @Operation(summary = "Get shoppinglists")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful response",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ShoppingListDTO.class))
            )
        ),
    })
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
    @Operation(summary = "Get a shoppinglist by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful response",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingListDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
    public ResponseEntity<ShoppingListDTO> getShoppinglistById(@PathVariable("id") Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);

        if (shoppingList.isPresent()) {
            return new ResponseEntity<>(mapper.toDto(shoppingList.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Creates a new shopping list.
    @PostMapping("/shoppinglists")
    @Operation(summary = "Create a new shopping list")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successful response",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingListDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
    public ResponseEntity<?> createShoppingList(@RequestBody ShoppingListCreateDTO shoppingListCreateDTO) {
        // Validate input data
        if (shoppingListCreateDTO.getName() == null || shoppingListCreateDTO.getName().isEmpty()) {
            return new ResponseEntity<>("Name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        // Create a new ShoppingList entity
        ShoppingList shoppingList = new ShoppingList(shoppingListCreateDTO.getName());

        // Save the new shopping list
        ShoppingList createdShoppingList = shoppingListRepository.save(shoppingList);

        return new ResponseEntity<>(mapper.toDto(createdShoppingList), HttpStatus.CREATED);
    }

    // Updates an existing shopping list with new values.
    @PutMapping("/shoppinglists/{id}")
    @Operation(summary = "Update an existing shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Void> updateShoppingList(@RequestBody ShoppingListCreateDTO updatedShoppingList,
            @PathVariable Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElse(null);
        if (shoppingList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        shoppingList.setName(updatedShoppingList.getName());
        shoppingList.setUpdatedAt(LocalDateTime.now());

        shoppingListRepository.save(shoppingList);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Deletes a shopping list and all its products.
    // JPA takes care of the products so we don't need to delete them explicitly.
    @DeleteMapping("/shoppinglists/{id}")
    @Operation(summary = "Delete a shoppinglist")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Successful response"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
    })
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
