package wlopera.lab.store.product;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import wlopera.lab.store.product.entity.Category;
import wlopera.lab.store.product.entity.Product;
import wlopera.lab.store.product.entity.repository.ProductRerpository;
import wlopera.lab.store.product.service.ProductService;
import wlopera.lab.store.product.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {

	@Mock
	private ProductRerpository productRerpository;

	private ProductService productService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		productService = new ProductServiceImpl(productRerpository);

		Category category = Category.builder().id(1L).build();

		Product computer = Product.builder().name("computer").category(category).description("")
				.stock(Double.parseDouble("5")).price(Double.parseDouble("12.75")).status("Created")
				.createAt(new Date()).build();

		Mockito.when(productRerpository.findById(1L)).thenReturn(Optional.of(computer));
		
		Mockito.when(productRerpository.save(computer)).thenReturn(computer);
	}
	
	@Test
	public void whenValidGetID_ThenReturnProduct() {
		Product found = productService.getProduct(1L);
		Assertions.assertThat(found.getName()).isEqualTo("computer");
	}
	
	@Test
	public void whenValidUpdateStock_ThenReturnNewStock() {
		Product newStock = productService.updateStock(1L, Double.parseDouble("8"));			
		Assertions.assertThat(newStock.getStock()).isEqualTo("13");
	}
}
