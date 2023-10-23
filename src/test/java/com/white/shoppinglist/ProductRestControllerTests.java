package com.white.shoppinglist;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ProductRepository;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ShoppingListRepository;
import com.white.shoppinglist.web.ProductCreateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestControllerTests {
    private final MockMvc mockMvc;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;
    private final String API_LOCATION = "/api";

    @Autowired
    public ProductRestControllerTests(
        MockMvc mockMvc,
        ShoppingListRepository shoppingListRepository,
        ProductRepository productRepository)
    {
        this.mockMvc = mockMvc;
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
    }

    @Test
    public void testGetAllProductsReturnsCorrectStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(API_LOCATION + "/products"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetProductByIdReturnsCorrectStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(API_LOCATION + "/products/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateProductReturnsCorrectStatus() throws Exception {
        ShoppingList shoppingList = shoppingListRepository.save(new ShoppingList());
        ProductCreateDTO productCreateDTO = new ProductCreateDTO("test", "test", shoppingList.getId(), 10.0, 2);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post(API_LOCATION + "/products")
            .content(objectMapper.writeValueAsString(productCreateDTO))
            .contentType("application/json"))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateProductReturnsCorrectStatus() throws Exception {
        ShoppingList shoppingList = shoppingListRepository.save(new ShoppingList());
        Product product = productRepository.save(new Product("test", "test", 5.0, 1, shoppingList));
        ProductCreateDTO productCreateDTO = new ProductCreateDTO("test", "test", shoppingList.getId(), 10.0, 2);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.put(API_LOCATION + "/products/" + product.getId())
            .content(objectMapper.writeValueAsString(productCreateDTO))
            .contentType("application/json"))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
