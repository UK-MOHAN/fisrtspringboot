package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Customers;
import com.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	// GET ALL
	public List<Customers> getAllCustomers() { // ✅ fixed: List<Customers> not List<CustomerRepository>
		return customerRepository.findAll();
	}

	// GET BY ID
	public Customers getCustomerById(int id) { // ✅ fixed: returns Customers not CustomerRepository
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found: " + id)); // ✅
																														// fixed:
																														// completed
																														// orElseThrow
	}

	// CREATE
	public Customers createCustomer(Customers customers) { // ✅ fixed: returns Customers
		// if (customerRepository.existsById(customers.get())) { // ✅ fixed:
		// getCustomerId()
		// throw new RuntimeException("Customer already exists!");
		// }
		return customerRepository.save(customers); // ✅ added: save and return
	}

	// DELETE
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}
}