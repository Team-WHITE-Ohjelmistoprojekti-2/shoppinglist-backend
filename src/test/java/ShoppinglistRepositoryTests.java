import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.white.shoppinglist.domain.ShoppingListRepository;
import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ShoppingList;
import com.white.shoppinglist.domain.ProductRepository;


//kolme testiä nyt alkuun, ei tosin toimi koska tuo postgre ei toimi. 
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppinglistRepositoryTests {

    @Autowired
    private ShoppingListRepository shoppinglistrepository;

    @Autowired
    private ProductRepository productrepository;

    @Test
    public void findByProductNameShouldReturnProduct() {
        List<Product> products = productrepository.findByName("Milk");
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Milk");
    }

    @Test 
    public void  ShoppingList() {
        ShoppingList shoppinglist = new ShoppingList("Ostoslista");
        shoppinglistrepository.save(shoppinglist);
        assertThat(shoppinglist.getId()).isNotNull();
    }

    @Test
    public void createNewProduct() {
        Product product1 = new Product("Peruna", "kova peruna", 0.79, 1, shoppinglistrepository.findByName("Ostoslista").get(0));
        productrepository.save(product1);
        assertThat(product1.getId()).isNotNull();
    }
}
