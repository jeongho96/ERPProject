package com.chonamzone.erpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.service.ManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManagementController {

	private final ManagementService managementService;

	@GetMapping("/management/all")
	public String managementAllList(@RequestParam int page, Model model) {
		model.addAttribute("managementList", managementService.getManagementAllList(page));
		model.addAttribute("totalPageCount", managementService.getTotalPageCount());
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	@GetMapping("/management/progress")
	public String managementProgressList(@RequestParam int page, Model model) {
		model.addAttribute("managementList", managementService.getManagementStatusList(page, "진행중"));
		model.addAttribute("totalPageCount", managementService.getTotalPageCount());
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	@GetMapping("/management/refuse")
	public String managementRefuseList(@RequestParam int page, Model model) {
		model.addAttribute("managementList", managementService.getManagementStatusList(page, "반려"));
		model.addAttribute("totalPageCount", managementService.getTotalPageCount());
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	@GetMapping("/management/approval")
	public String managementApprovalList(@RequestParam int page, Model model) {
		model.addAttribute("managementList", managementService.getManagementStatusList(page, "최종승인"));
		model.addAttribute("totalPageCount", managementService.getTotalPageCount());
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	@GetMapping("/management/travels/{dSeq}")
	public String getManagementTravel(@PathVariable int dSeq, Model model) {
		model.addAttribute("travel", managementService.getManagementTravel(dSeq));
		model.addAttribute("partnames", managementService.getPartnameWithUsernameAll());
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


	 @PutMapping("/management/travels/{dSeq}") 
	 public String updateTravels(@PathVariable int dSeq, @ModelAttribute TravelDTO.MGVacationDTO travel) { 
		 
		 managementService.updateTravels(travel);
	 
		 return "redirect:/management/all?page=1";
	 
	 }
	 

}
