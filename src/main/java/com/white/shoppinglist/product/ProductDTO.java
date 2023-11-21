package com.white.shoppinglist.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;;

// Product data transfer object.
@Getter
@Setter
@NoArgsConstructor
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
}
