package com.chonamzone.erpproject.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.service.ManagementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManagementApiController {
	
	private final ManagementService managementService;
	
	@GetMapping("/api/management/all")
	public List<DocumentListDTO.Response> getManagementList(@RequestParam int page){
		// page 시작 1로 가정
		return managementService.getManagementList(page);
	}
	
	
	@GetMapping("/api/management/travels/{dSeq}")
	public TravelDTO getManagementTravel(@PathVariable int dSeq) {
		return managementService.getManagementTravel(dSeq);
	}
	
	
	@GetMapping("/api/management/vacations/{dSeq}")
	public VacationDTO getManagementVacation(@PathVariable int dSeq) {
		return managementService.getManagementVacation(dSeq);
	}
	
}
