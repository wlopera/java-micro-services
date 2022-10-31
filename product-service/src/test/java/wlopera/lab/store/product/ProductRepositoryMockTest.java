package wlopera.lab.store.product;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import wlopera.lab.store.product.entity.Category;
import wlopera.lab.store.product.entity.Product;
import wlopera.lab.store.product.entity.repository.ProductRerpository;

@DataJpaTest
public class ProductRepositoryMockTest {
	
	@Autowired
	private ProductRerpository productRerpository;
	
	@Test
	public void whenFindByCategory_thenReturnListProduct() {
		
		Category category = Category.builder().id(1L).build();
		
//		Product product = Product.builder()
//				.name("computer")
//				.category(category)
//				.description("")
//				.stock(Double.parseDouble("10"))
//				.price(Double.parseDouble("1240.99"))
//				.status("Created")
//				.createAt(new Date())
//				.build();
//		
//		productRerpository.save(product);
		
		
		List<Product> founds = productRerpository.findByCategory(category);
		
		Assertions.assertThat(founds.size()).isEqualTo(6);
	}

}
