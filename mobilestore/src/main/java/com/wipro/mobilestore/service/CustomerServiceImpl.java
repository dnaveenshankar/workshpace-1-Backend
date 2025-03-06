package com.wipro.mobilestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.mobilestore.entity.Cart;
import com.wipro.mobilestore.entity.Customer;
import com.wipro.mobilestore.entity.Order;
import com.wipro.mobilestore.exception.ResourceNotFoundException;
import com.wipro.mobilestore.repository.CartRepository;
import com.wipro.mobilestore.repository.CustomerRepository;
import com.wipro.mobilestore.repository.OrderRepository;

@Service
public class CustomerServiceImpl implements CustomerService  {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderRepository orderRepository;
 
	@Override
	public Customer saveCustomer(Customer customer) {
		
		customerRepository.save(customer);
		
		Cart cart = new Cart();
		cart.setCartTotal(0);
		cart.setCustomer(customer);
		
		cartRepository.save(cart);
		return customer;
	}
 
	@Override
	public Customer getCustomerById(int customerId) {
		
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with id: "+customerId);
		}
		Customer customer = optionalCustomer.get();		
		return customer;
	}
 
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
	    Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());

	    if (optionalCustomer.isEmpty()) {
	        throw new ResourceNotFoundException("Customer not existing with id: " + customer.getCustomerId());
	    }
	    customerRepository.save(customer);
	    return customer;
	}
	
	@Override
	public void deleteCustomer(int customerId) {
	    Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
	    
	    if (optionalCustomer.isEmpty()) {
	        throw new ResourceNotFoundException("Customer not existing with id: " + customerId);
	    }

	    Customer customer = optionalCustomer.get();
	    System.out.println("Customer found: " + customer.getCustomerId());

	    // Debugging: Print related entities
	    System.out.println("Orders associated: " + customer.getOrders().size());
	    System.out.println("Cart associated: " + (customer.getCart() != null ? "Exists" : "None"));

	    // Delete all orders and related order items
	    orderRepository.deleteAll(customer.getOrders());

	    // Delete cart if exists
	    if (customer.getCart() != null) {
	        cartRepository.delete(customer.getCart());
	    }

	    // Delete customer
	    customerRepository.delete(customer);
	    System.out.println("Customer deleted successfully");
	}



}
