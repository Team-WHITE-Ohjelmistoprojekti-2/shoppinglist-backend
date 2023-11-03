package com.white.shoppinglist.product;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotNull;

// Product data transfer object used to create and update products.
public class ProductCreateDTO {
    private String name;
    private String details;
    private Long shoppinglistId;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    @NotNull
    private int quantity = 1;

    public ProductCreateDTO(String name, String details, Long shoppinglistId, @PositiveOrZero @NotNull Double price,
            @PositiveOrZero @NotNull int quantity) {
        this.name = name;
        this.details = details;
        this.shoppinglistId = shoppinglistId;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductCreateDTO() {}

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Long getShoppinglistId() {
        return shoppinglistId;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setShoppinglistId(Long shoppinglistId) {
        this.shoppinglistId = shoppinglistId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductCreateDTO [name=" + name + ", details=" + details + ", shoppinglistId=" + shoppinglistId
                + ", price=" + price + ", quantity=" + quantity + "]";
    }
}
