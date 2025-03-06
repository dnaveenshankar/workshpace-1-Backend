package com.wipro.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.mobilestore.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
