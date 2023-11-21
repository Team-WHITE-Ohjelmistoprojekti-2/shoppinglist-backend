package com.white.shoppinglist.product;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// Product data transfer object used to create and update products.
@Getter
@Setter
@NoArgsConstructor
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
}
