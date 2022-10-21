package wlopera.lab.store.product.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import wlopera.lab.store.product.entity.Category;
import wlopera.lab.store.product.entity.Product;

public interface ProductRerpository extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(Category category);

}
