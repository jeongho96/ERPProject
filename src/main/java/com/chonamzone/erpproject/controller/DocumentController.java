package com.chonamzone.erpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {

	
	@GetMapping("/document")
	public String documentcontroller() {
		return"document";
	}
	
	@GetMapping("/document/vacation")
	public String documentvacationcontroller() {
		return"documentvacation";
	}
	
	@GetMapping("/document/travel")
	public String documenttravelcontroller() {
		return"documenttravel";
	}

}
