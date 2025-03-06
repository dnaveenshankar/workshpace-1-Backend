package com.wipro.orderservice.service;

import java.util.List;

import com.wipro.orderservice.entity.Order;
import com.wipro.orderservice.entity.OrderItem;
import com.wipro.orderservice.model.InvoiceDTO;

public interface OrderService {

    Order placeOrder(int customerId, List<OrderItem> orderItems);

    Order getOrderDetails(int orderId);
    
    InvoiceDTO generateInvoice(int orderId);
}
