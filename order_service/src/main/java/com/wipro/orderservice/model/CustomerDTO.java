package com.wipro.orderservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String city;

}
