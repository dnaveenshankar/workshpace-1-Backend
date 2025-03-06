package com.wipro.mobilestore.service;

import java.util.List;
import com.wipro.mobilestore.entity.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer getCustomerById(int customerID);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Customer customer);
    void deleteCustomer(int customerID);
}
