package com.wipro.mobilestore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.mobilestore.entity.Cart;
import com.wipro.mobilestore.entity.CartItem;
import com.wipro.mobilestore.entity.Mobile;
import com.wipro.mobilestore.exception.ResourceNotFoundException;
import com.wipro.mobilestore.repository.CartItemRepository;
import com.wipro.mobilestore.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	@Autowired
	private MobileService mobileService;
	
	
	public Cart addMobileToCart(int cartId, int mobileId, int qty) {
		Mobile mobile = mobileService.fetchMobileById(mobileId);
		double price = mobile.getPrice();
		double itemTotal = qty * price;
		
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if (optionalCart.isEmpty()) {
			throw new ResourceNotFoundException("Cart not found with id: " + cartId);
		}
		
		Cart cart = optionalCart.get();
		
		CartItem cartItem = new CartItem();
		
		//cartItem.setCartItemId(0);
		cartItem.setMobileId(mobileId);
		cartItem.setQty(qty);
		cartItem.setItemToatl(itemTotal);
		cartItem.setCart(cart);
		cartItemRepository.save(cartItem);
		
		double cartTotal = 0;
		List<CartItem> cartItems = cart.getCartItems();
		
		for(CartItem item : cartItems) {
			double total = item.getItemToatl();
			cartTotal = cartTotal + total;
		}
		
		cart.setCartTotal(cartTotal);
		cartRepository.save(cart);
		return cart;
	}
	
}