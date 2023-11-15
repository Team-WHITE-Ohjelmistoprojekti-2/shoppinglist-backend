package com.white.shoppinglist.shoppinglist;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// Shoppinglist data transfer object used to create shoppinglists.
@Getter
@Setter
@NoArgsConstructor
public class ShoppingListCreateDTO {
    private String name;

    public ShoppingListCreateDTO(String name) {
        this.name = name;
    }

    // No need to write a separate no-argument constructor, Lombok generates it with @NoArgsConstructor

    // No need to write explicit getters and setters, Lombok generates them with @Getter and @Setter

    @Override
    public String toString() {
        return "ShoppingListCreateDTO [name=" + name + "]";
    }
}
