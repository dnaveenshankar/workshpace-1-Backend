package com.wipro.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.mobilestore.entity.Customer;

public interface CustomerRepository extends JpaRepository <Customer, Integer>{
	

}
