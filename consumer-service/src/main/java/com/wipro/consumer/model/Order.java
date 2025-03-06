package com.wipro.consumer.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {

	private int orderId;
	
	private double orderAmount;
	
	private String customerName;
	
	private String email;
	
	private String mobile;
}

