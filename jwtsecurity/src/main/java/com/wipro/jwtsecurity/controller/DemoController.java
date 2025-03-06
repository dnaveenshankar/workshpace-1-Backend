package com.wipro.jwtsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard() {
		return "Admin Dashboard";
		
	}
	@GetMapping("/user/dashboard")
	public String userDashboard() {
		return "User Dashboard";
	}
	
	@GetMapping("/guest/dashboard")
	public String guestDashboard() {
		return "Guest dashboard";
	}

}
