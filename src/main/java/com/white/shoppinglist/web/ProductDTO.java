package com.white.shoppinglist.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

// Product data transfer object.
public class ProductDTO {
    private Long id;
    private String name;
    private String details;
    private Long shoppinglistId;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    @NotNull
    private int quantity = 1;

    public ProductDTO(Long id, String name, String details, Double price, int quantity, Long shoppinglistId) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.price = price;
        this.quantity = quantity;
        this.shoppinglistId = shoppinglistId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getShoppinglistId() {
        return shoppinglistId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShoppinglistId(Long shoppinglistId) {
        this.shoppinglistId = shoppinglistId;
    }

    @Override
    public String toString() {
        return "ProductDTO [id=" + id + ", name=" + name + ", details=" + details + ", price=" + price + ", quantity="
                + quantity + ", shoppinglistId=" + shoppinglistId + "]";
    }
}
