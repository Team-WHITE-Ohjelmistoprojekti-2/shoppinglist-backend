package com.white.shoppinglist;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ShoppingListRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestControllerTests {
    private final MockMvc mockMvc;
    private final ShoppingListRepository shoppingListRepository;
    private final String API_LOCATION = "/api";

    @Autowired
    public ProductRestControllerTests(
        MockMvc mockMvc,
        ShoppingListRepository shoppingListRepository)
    {
        this.mockMvc = mockMvc;
        this.shoppingListRepository = shoppingListRepository;
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
        List<ShoppingList> shoppingLists = (List<ShoppingList>) shoppingListRepository.findAll();
        ShoppingList shoppingList = shoppingLists.get(0);

        mockMvc.perform(MockMvcRequestBuilders.post(API_LOCATION + "/products")
            .content("{\"name\":\"test product\","
                + "\"details\": \"test detail\","
                + "\"price\": 10,"
                + "\"quantity\": 2,"
                + "\"shoppinglistId\": " + shoppingList.getId() + "}")
            .contentType("application/json"))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
