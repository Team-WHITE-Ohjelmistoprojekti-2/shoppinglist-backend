package com.white.shoppinglist.web;

// Shopping list data transfer object.
public class ShoppingListDTO {
    private Long id;
    private String name;

    public ShoppingListDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public ShoppingListDTO() {
        // Default constructor with no parameters
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShoppingListDTO [id=" + id + ", name=" + name + "]";
    }
}
