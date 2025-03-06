package com.wipro.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.mobilestore.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

}
