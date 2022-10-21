package wlopera.lab.store.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import wlopera.lab.store.customer.entity.Customer;
import wlopera.lab.store.customer.entity.Region;
import wlopera.lab.store.customer.service.CustomerService;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> listAllCustomer(
			@RequestParam(name = "regionId", required = false) Long regionId) {

		List<Customer> customers = new ArrayList<>();

		if (null == regionId) {
			customers = customerService.findCustomerAll();
			if (customers.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			Region region = new Region();
			region.setId(regionId);

			customers = customerService.findCustomerByRegion(region);
			if (null == customers) {
				log.error("Cliente con región  id {} no encontrada.", regionId);
				return ResponseEntity.notFound().build();
			}
		}

		return ResponseEntity.ok(customers);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {

		log.info("Buscando clientes con id {}", id);

		Customer customer = customerService.getCustomer(id);
		if (null == customer) {
			log.error("Cliente con id {} no encontrada.", id);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(customer);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {

		log.info("Creando clientes: {}", customer);

		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage(result));
		}

		Customer customerDB = customerService.createCustomer(customer);

		return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {

		log.info("Actualizando cliente con id {}", id);

		Customer currentCustomer = customerService.getCustomer(id);

		if (null == currentCustomer) {
			log.error("Imposible actualizar. Cliente con id {} no encontrado", id);
			return ResponseEntity.notFound().build();
		}

		customer.setId(id);
		currentCustomer = customerService.updateCustomer(customer);
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {

		log.info("Buscando y borrando al cliente con id {}", id);

		Customer customer = customerService.getCustomer(id);

		if (null == customer) {
			log.error("Imposible borrar. Cliente con id {} no encontrado", id);
			return ResponseEntity.notFound().build();
		}
		
		customer = customerService.deleteCustomer(customer);
		return ResponseEntity.ok(customer);
	}

	private String formatMessage(BindingResult result) {

		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<String, String>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());
		ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

}
