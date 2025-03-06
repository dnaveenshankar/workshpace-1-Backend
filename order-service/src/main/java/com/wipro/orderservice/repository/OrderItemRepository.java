package com.wipro.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.orderservice.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository <OrderItem, Integer> {

}
