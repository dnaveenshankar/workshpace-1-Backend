package com.wipro.mobilestore.service;

import com.wipro.mobilestore.entity.Order;

public interface OrderService {

	Order placeOrder(int customerIdD);

	Order getOrderDetails(int orderId);
}