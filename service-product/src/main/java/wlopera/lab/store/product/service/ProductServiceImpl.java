package wlopera.lab.store.product.service;

import java.util.Date;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import wlopera.lab.store.product.entity.Category;
import wlopera.lab.store.product.entity.Product;
import wlopera.lab.store.product.entity.repository.ProductRerpository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRerpository productRerpository;

	@Override
	public List<Product> listAllProduct() {
		return productRerpository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		return productRerpository.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		product.setStatus("CREATED");
		product.setCreateAt(new Date());

		return productRerpository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Product productDB = getProduct(product.getId());

		if (null == productDB) {
			return null;
		}

		productDB.setCategory(product.getCategory());
		// productDB.setCreateAt(product.getCreateAt());
		product.setCreateAt(new Date());
		productDB.setDescription(product.getDescription());
		productDB.setName(product.getName());
		productDB.setPrice(product.getPrice());
		productDB.setStatus(product.getStatus());
		productDB.setStock(product.getStock());

		return productRerpository.save(productDB);
	}

	@Override
	public Product deleteProduct(Long id) {
		Product productDB = getProduct(id);

		if (null == productDB) {
			return null;
		}

		productDB.setStatus("DELETED");
		productDB.setCreateAt(new Date());

		return productRerpository.save(productDB);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRerpository.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quality) {
		Product productDB = getProduct(id);

		if (null == productDB) {
			return null;
		}
		Double stock = productDB.getStock() + quality;
		
		productDB.setStock(stock);
		return productRerpository.save(productDB);
	}

}
