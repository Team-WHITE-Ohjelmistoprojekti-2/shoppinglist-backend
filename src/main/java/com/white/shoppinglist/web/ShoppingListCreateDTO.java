package com.white.shoppinglist.web;

// Shoppinglist data transfer object used to create shoppinglists.
public class ShoppingListCreateDTO {
    private String name;

    public ShoppingListCreateDTO(String name) {
        this.name = name;
    }

    public ShoppingListCreateDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShoppingListCreateDTO [name=" + name + "]";
    }
}
