package com.wipro.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.orderservice.model.MobileDTO;

@FeignClient(value = "MOBILE-SERVICE")
public interface MobileApiClient {

	@GetMapping("/mobiles/{mobileId}")
	MobileDTO getMobileDetails(@PathVariable int mobileId);
}
