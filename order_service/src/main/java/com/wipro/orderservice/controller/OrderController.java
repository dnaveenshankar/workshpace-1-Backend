package com.wipro.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.orderservice.entity.Order;
import com.wipro.orderservice.entity.OrderItem;
import com.wipro.orderservice.model.InvoiceDTO;
import com.wipro.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable int customerId, @RequestBody List<OrderItem> orderItems){
    	Order order = orderService.placeOrder(customerId, orderItems);   	
    	return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId){
    	Order order = orderService.getOrderDetails(orderId);   	
    	return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    @GetMapping("/invoice")
    public ResponseEntity<InvoiceDTO> getInvoiceDetails(@RequestParam("orderId") int orderId) {
        InvoiceDTO invoice = orderService.generateInvoice(orderId);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    
}
