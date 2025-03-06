package com.wipro.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wipro.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository <Order, Integer> {

}
