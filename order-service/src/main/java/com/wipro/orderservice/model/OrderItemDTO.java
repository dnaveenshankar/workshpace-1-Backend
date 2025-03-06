package com.wipro.orderservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

	private int orderItemId;
	private MobileDTO mobile;
	private int qty;
	private double itemTotal;
}
