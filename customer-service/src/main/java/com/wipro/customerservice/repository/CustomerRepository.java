package com.wipro.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wipro.customerservice.entity.Customer;

public interface CustomerRepository extends JpaRepository <Customer, Integer>{
	

}
