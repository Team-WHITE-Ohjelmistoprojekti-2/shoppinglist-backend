import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.white.shoppinglist.domain.ShoppingListRepository;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ProductRepository;


//kolme testiä nyt alkuun, ei tosin toimi koska tuo postgre ei toimi. 
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppinglistRepositoryTests {

    @Autowired
    private ShoppingListRepository shoppinglistrepository;

    @Autowired
    private ProductRepository productrepository;

    @Test
    public void findByProductNameShouldReturnProduct() {
<<<<<<< HEAD
    List<Product> products = productrepository.findByName("Peruna");
    assertThat(products).hasSize(1);
    assertThat(products.get(0).getName()).isEqualTo("Peruna");
}
=======
        List<Product> products = productrepository.findByName("Milk");
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Milk");
    }
>>>>>>> 3d05f37e43e2001ae9a4947b178476578c63ee6c

    @Test
    public void createShoppingList() {
    ShoppingList shoppinglist = new ShoppingList("Ostoslista");
    shoppinglistrepository.save(shoppinglist);
    assertThat(shoppinglist.getId()).isNotNull();
}

    @Test
    public void createNewProduct() {
    ShoppingList shoppinglist = new ShoppingList("Ostoslista");
    shoppinglistrepository.save(shoppinglist);
    
    Product product1 = new Product("Peruna", "kova peruna", 0.79, 1, shoppinglist);
    productrepository.save(product1);
    
    assertThat(product1.getId()).isNotNull();
    }
}
