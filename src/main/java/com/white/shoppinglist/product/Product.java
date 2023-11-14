package com.white.shoppinglist.product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.white.shoppinglist.shoppinglist.ShoppingList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String details;
    
    // some validation to ensure price can be null MIGHT NEED EXTRA WORK the 0.0 gives some failures with null
    @PositiveOrZero
    private Double price = 0.0;
    
    // Add validation to ensure that the value cannot be negative
    @NotNull
    @PositiveOrZero
    private int quantity = 1; // Default to 1 if not provided
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    public Product(String name, String details, Double price, int quantity, ShoppingList shoppingList) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.quantity = quantity;
        this.shoppingList = shoppingList;
    }

    public Product() {
        this.quantity = 1;
    }
}
