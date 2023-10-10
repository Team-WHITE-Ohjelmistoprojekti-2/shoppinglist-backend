package com.white.shoppinglist.web;

import java.time.LocalDateTime;

// Shopping list data transfer object.
public class ShoppingListDTO {
    private Long id;
    private String name;

    // Date and time when created in ISO-8601 format
    private LocalDateTime createdAt;

    // Date and time when updated in ISO-8601 format
    private LocalDateTime updatedAt;

    public ShoppingListDTO(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ShoppingListDTO [id=" + id + ", name=" + name + "]";
    }
}
