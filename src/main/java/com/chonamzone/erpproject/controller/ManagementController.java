package com.chonamzone.erpproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonamzone.erpproject.model.DocumentListDTO.MGResponse;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.service.ManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManagementController {

	private final ManagementService managementService;

	/**
	 * 전체 문서 목록 보기
	 * @param page
	 * @param model
	 * @return 전체 문서 목록 화면
	 */
	@GetMapping("/management/all")
	public String managementAllList(@RequestParam int page, Model model) {
		List<MGResponse> managementList = managementService.getManagementAllList(page);
		model.addAttribute("managementList", managementList);
		model.addAttribute("totalPageCount", (managementList.size() / 10) + 1);
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	/**
	 * 결재 상태가 진행중인 문서 목록 보기
	 * @param page
	 * @param model
	 * @return 결재 상태가 진행중인 문서 목록 화면
	 */
	@GetMapping("/management/progress")
	public String managementProgressList(@RequestParam int page, Model model) {
		List<MGResponse> managementList = managementService.getManagementStatusList(page, "진행중");
		model.addAttribute("managementList", managementList);
		model.addAttribute("totalPageCount", (managementList.size() / 10) + 1);
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	/**
	 * 결재 상태가 반려인 문서 목록 보기
	 * @param page
	 * @param model
	 * @return 결재 상태가 반려인 문서 목록 화면
	 */
	@GetMapping("/management/refuse")
	public String managementRefuseList(@RequestParam int page, Model model) {
		List<MGResponse> managementList = managementService.getManagementStatusList(page, "반려");
		model.addAttribute("managementList", managementList);
		model.addAttribute("totalPageCount", (managementList.size() / 10) + 1);
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	/**
	 * 결재 상태가 승인인 문서 목록 보기
	 * @param page
	 * @param model
	 * @return 결재 상태가 승인인 문서 목록 화면
	 */
	@GetMapping("/management/approval")
	public String managementApprovalList(@RequestParam int page, Model model) {
		List<MGResponse> managementList = managementService.getManagementStatusList(page, "최종승인");
		model.addAttribute("managementList", managementList);
		model.addAttribute("totalPageCount", (managementList.size() / 10) + 1);
		model.addAttribute("nowPage", page);

		return "managementList";
	}

	
	/**
	 * 출장보고서 상세 보기
	 * @param dSeq
	 * @param model
	 * @return 출장보고서 상세 화면
	 */
	@GetMapping("/management/travels/{dSeq}")
	public String getManagementTravel(@PathVariable int dSeq, Model model) {
		model.addAttribute("travel", managementService.getManagementTravel(dSeq));
		model.addAttribute("partnames", managementService.getPartnameWithUsernameAll());
		return "managementTravel";
	}

	/**
	 * 휴가신청서 상세 보기
	 * @param dSeq
	 * @param model
	 * @return 휴가신청서 상세 화면
	 */
	@GetMapping("/management/vacations/{dSeq}")
	public String managementVacation(@PathVariable int dSeq, Model model) {
		model.addAttribute("vacation", managementService.getManagementVacation(dSeq));
		model.addAttribute("partnames", managementService.getPartnameWithUsernameAll());
		return "managementVacation";
	}

	/**
	 * 출장보고서 수정 화면
	 * @param dSeq
	 * @param travel
	 * @param model
	 * @return 수정 완료 메시지
	 */
	 @PutMapping("/management/travels/{dSeq}") 
	 public String updateTravels(@PathVariable int dSeq, @ModelAttribute TravelDTO.MGVacationDTO travel, Model model) { 
		 managementService.updateTravels(travel);
		 
		 model.addAttribute("message", "수정이 완료되었습니다.");
		 model.addAttribute("successUrl", "/management/all?page=1");
			
		 return "message";
	 }
	

	/**
	 * 휴가신청서 수정 화면
	 * @param dSeq
	 * @param vacation 
	 * @param model
	 * @return 수정 완료 메시지
	 */
	@PutMapping("/management/vacations/{dSeq}")
	public String updateVacations(@PathVariable int dSeq, @ModelAttribute VacationDTO.MGVacationDTO vacation, Model model) {
		managementService.updateVacations(vacation);
		
		model.addAttribute("message", "수정이 완료되었습니다.");
		model.addAttribute("successUrl", "/management/all?page=1");
		
		return "message";
	}
	 

}
