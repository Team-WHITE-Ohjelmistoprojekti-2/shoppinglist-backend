package com.white.shoppinglist.shoppinglist;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// Shopping list data transfer object.
@Getter
@Setter
@NoArgsConstructor
public class ShoppingListDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ShoppingListDTO(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // No need to write a separate no-argument constructor, Lombok generates it with @NoArgsConstructor

    // No need to write explicit getters and setters, Lombok generates them with @Getter and @Setter
}
