package com.wipro.customerservice.service;

import java.util.List;

import com.wipro.customerservice.entity.Customer;

public interface CustomerService {

	Customer saveCustomer (Customer customer);
	Customer getCustomerById (int customerID);
	List <Customer> getAllCustomers();
	
}
