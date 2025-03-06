package com.wipro.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.mobilestore.entity.Order;

public interface OrderRepository extends JpaRepository <Order, Integer> {

}
