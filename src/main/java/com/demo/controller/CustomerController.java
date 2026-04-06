package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.entity.Customers;
import com.demo.kafaka.CustomerProducer;
import com.demo.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")        // ✅ fixed: typo 'cutomers' → 'customers'
public class CustomerController {

	@Autowired
    private  CustomerService customerService;  // ✅ fixed: removed underscores
	
	@Autowired
	private CustomerProducer customerProducer;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable int id) {  // ✅ fixed: @PathVariable spelling
        return ResponseEntity.ok(customerService.getCustomerById(id));         // ✅ fixed: added semicolon
    }

    // CREATE
   @PostMapping
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customers) {
	   Customers savedMsg = customerService.createCustomer(customers);
       //return ResponseEntity.ok();
       customerProducer.sendMessage(savedMsg);
       return ResponseEntity.ok(savedMsg);
       
   }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
    
    
}