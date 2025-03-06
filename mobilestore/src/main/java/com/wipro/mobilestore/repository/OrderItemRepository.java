package com.wipro.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.mobilestore.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository <OrderItem, Integer> {

}
