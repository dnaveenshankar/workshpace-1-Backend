package com.wipro.mobilestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.mobilestore.entity.Cart;
import com.wipro.mobilestore.model.CartItemRequest;
import com.wipro.mobilestore.service.CartService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/addItem")
	public ResponseEntity<Cart> addToCart(@RequestBody CartItemRequest cartItemReq){
		Cart cart = cartService.addMobileToCart(cartItemReq.getCartId(), cartItemReq.getMobileId(), cartItemReq.getQty()); 
		return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
		
	}
}
