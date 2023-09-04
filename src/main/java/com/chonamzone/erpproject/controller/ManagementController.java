package com.chonamzone.erpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ManagementController {
	
	@GetMapping("/management/all")
	public String managementAllList() {
		return "managementAllList";
	}
	
	@GetMapping("/management/travels/{dSeq}")
	public String managementTravel(@PathVariable int dSeq) {
		return "managementTravel";
	}
	
	@GetMapping("/management/vacations/{dSeq}")
	public String managementVacation(@PathVariable int dSeq) {
		return "managementVacation";
	}
	
	@GetMapping("/test")
	public String Unauthorized() {
		return "managementUnauthorized";
	}
}
