package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
	// ✅ fixed: Integer not Int (must be wrapper class, not primitive)
	// ✅ fixed: removed wrong List<Customers> findById() — JpaRepository already
	// provides it
}