package wlopera.lab.store.customer.service;

import java.util.List;

import wlopera.lab.store.customer.entity.Customer;
import wlopera.lab.store.customer.entity.Region;

public interface CustomerService {

	public List<Customer> findCustomerAll();

	public List<Customer> findCustomerByRegion(Region region);

	public Customer createCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);
	
	public Customer deleteCustomer(Customer customer);

	public Customer getCustomer(Long id);

}
