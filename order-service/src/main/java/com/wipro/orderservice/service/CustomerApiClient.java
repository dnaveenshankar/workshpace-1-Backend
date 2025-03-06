package com.wipro.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.orderservice.model.CustomerDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(value="CUSTOMER-SERVICE")

public interface CustomerApiClient {

    @CircuitBreaker(name="customerApi", fallbackMethod = "getCustomerDetailsFallBack")
	@GetMapping("/customer/{customerId}")
	CustomerDTO getCustomerDetails(@PathVariable int customerId);
	
	default CustomerDTO getCustomerDetailsFallBack(Throwable t) {
				
		return new CustomerDTO();
	}
}
