package com.chonamzone.erpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.service.ManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManagementController {
	
	private final ManagementService managementService;
	
	@GetMapping("/management/all")
	public String managementAllList(@RequestParam int page, Model model) {
		model.addAttribute("managementAllList", managementService.getManagementList(page));
		return "managementAllList";
	}
	
	@GetMapping("/management/travels/{dSeq}")
	public String getManagementTravel(@PathVariable int dSeq, Model model) {
		model.addAttribute("travel", managementService.getManagementTravel(dSeq));
		return "managementTravel";
	}
	
	@GetMapping("/management/vacations/{dSeq}")
	public String managementVacation(@PathVariable int dSeq, Model model) {
		model.addAttribute("vacation", managementService.getManagementVacation(dSeq));
		model.addAttribute("partnames", managementService.getPartnameWithUsernameAll());
		return "managementVacation";
	}
	
	@PutMapping("/management/vacations/{dSeq}")
	public String updateVacations(@PathVariable int dSeq, @ModelAttribute VacationDTO.MGVacationDTO vacation) {
		managementService.updateVacations(vacation);
		return "redirect:/management/all?page=1";
		
	}
	
	@GetMapping("/test")
	public String Unauthorized() {
		return "managementUnauthorized";
	}
	
}
