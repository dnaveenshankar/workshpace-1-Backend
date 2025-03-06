package com.wipro.mobilestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CartItemRequest {

	private int cartId;
	private int mobileId;
	private int qty;
}
