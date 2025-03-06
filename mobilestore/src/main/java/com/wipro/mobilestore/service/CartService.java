package com.wipro.mobilestore.service;

import com.wipro.mobilestore.entity.Cart;

public interface CartService {
	
	Cart addMobileToCart(int cartID, int mobileId, int qty);

	//Cart removeMobileFromCart(int cartID, int mobileId);
	
	//Cart updateQtyInCart(int cartID, int mobileId, int qty);
}
