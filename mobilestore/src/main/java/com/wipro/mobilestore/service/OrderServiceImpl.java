package com.wipro.mobilestore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.mobilestore.entity.Order;
import com.wipro.mobilestore.entity.OrderItem;
import com.wipro.mobilestore.exception.ResourceNotFoundException;
import com.wipro.mobilestore.entity.Cart;
import com.wipro.mobilestore.entity.CartItem;
import com.wipro.mobilestore.entity.Customer;
import com.wipro.mobilestore.repository.CartItemRepository;
import com.wipro.mobilestore.repository.CartRepository;
import com.wipro.mobilestore.repository.OrderItemRepository;
import com.wipro.mobilestore.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartRepository cartRepository;


    @Override
    public Order placeOrder(int customerID) {
        Customer customer = customerService.getCustomerById(customerID); 
        Cart cart = customer.getCart(); 
        List<CartItem> cartItems = cart.getCartItems(); 
        Order order = new Order();
        
        order.setOrderAmount(cart.getCartTotal());
        order.setOrderStatus("Pending");
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        
        List<OrderItem> orderItems = new ArrayList<>();
        order = orderRepository.save(order);    
        
        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemTotal(item.getItemToatl()); 
            orderItem.setMobileId(item.getMobileId());
            orderItem.setQty(item.getQty());
            orderItem.setOrder(order);
            
            orderItems.add(orderItem);
            //orderItemRepository.save(orderItem);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        
        //clear the cart data 
        for (CartItem item: cartItems) { 
        	cartItemRepository.delete(item);  
        } 
        cart.setCartItems(null);;
        cart.setCartTotal(0); //reset the cart total 
        cartRepository.save(cart);//Update the cart
                
        return order;
    }

    @Override 
    public Order getOrderDetails(int orderId) { 
        Optional<Order> optionalOrder = orderRepository.findById(orderId); 
        if (optionalOrder.isEmpty()) { 
            throw new ResourceNotFoundException("Order not found with id: " + orderId); 
        } 
        Order order = optionalOrder.get(); 
        return order; 
    }

}

