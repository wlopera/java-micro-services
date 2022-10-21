package wlopera.lab.store.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import wlopera.lab.store.customer.entity.Customer;
import wlopera.lab.store.customer.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByNumberID(String numberId);

	public List<Customer> findByLastName(String lastName);

	public List<Customer> findByRegion(Region region);

}
