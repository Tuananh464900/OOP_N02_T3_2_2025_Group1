import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
void testGetAllProducts() {
    List<Product> mockProducts = Arrays.asList(
        new Product("Product A", 100, 10.0),
        new Product("Product B", 200, 20.0)
    );

    when(productRepository.findAll()).thenReturn(mockProducts);

    List<Product> result = productService.getAllProducts();
    assertEquals(2, result.size());
    assertEquals("Product A", result.get(0).getName());
    assertEquals(100, result.get(0).getQuantity());
}
}
