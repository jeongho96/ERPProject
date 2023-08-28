package com.chonamzone.erpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {
	
	@GetMapping("/management")
	public String managementList() {
		return "managementList";
	}
	
	@GetMapping("/test")
	public String Unauthorized() {
		return "managementUnauthorized";
	}
}
