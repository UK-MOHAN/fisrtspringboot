package com.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Customers")
@Data
public class Customers {

	@Id
	private int customerid; // ✅ matches MySQL column exactly

	private String first_name; // ✅ matches MySQL column exactly

	private String last_name; // ✅ matches MySQL column exactly

	private String email;

	private String city;

	private int age;

	private String phone;
}