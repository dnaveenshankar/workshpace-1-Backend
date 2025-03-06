package com.wipro.security_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

	
	@GetMapping("/home")
	public String home() {
		
		return "Home";
	}
	
	@GetMapping("/user/dashboard")
	public String userDashboard() {
		
		return "User Dashboard";
	}
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard() {
		
		return "Admin Dashboard";
	}
}
