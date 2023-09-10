package com.chonamzone.erpproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.service.DocumentService;

@Controller
public class DocumentController {

	@Autowired // 의존성 주입 객체 자동 생성 기능.
	private DocumentService documentService;
	
	
	/**
	 * 작성할 기안서 선택 페이지
	 * @return 작성할 기안서 선택 화면
	 */
	@GetMapping("/document")
	public String documentcontroller() {
		return "document";
	}
	
	/**
	 * 휴가 신청서 작성 페이지
	 * @param model
	 * @param session
	 * @return 휴가 신청서 작성 화면
	 */
	@GetMapping("/document/vacation")
	public String documentvacationcontroller(Model model, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");
		
		VacationDTO.MGVacationDTO vacation = new VacationDTO.MGVacationDTO();
		vacation.setUserData(userDTO);
		vacation.setPName(documentService.getPartname(userDTO.getPId()));
		
		model.addAttribute("vacation", vacation);
		return "documentVacation";
	}
	
	/**
	 * 출장 보고서 작성 페이지
	 * @param model
	 * @param session
	 * @return 출방보고서 작성 화면
	 */
	@GetMapping("/document/travel")
	public String documenttravelcontroller(Model model, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");
		
		TravelDTO.MGVacationDTO travel = new TravelDTO.MGVacationDTO();
		travel.setUserData(userDTO);
		travel.setPName(documentService.getPartname(userDTO.getPId()));
		
		model.addAttribute("travel", travel);
		return "documentTravel";
	}
	
	
	/**
	 * 휴가 신청서 데이터 저장
	 * @param model
	 * @param vacation
	 * @param session
	 * @return 작성 완료 메시지
	 */
	@PostMapping("/document/vacation") 
	public String documentVacationInsertController(Model model, @ModelAttribute("vacation") VacationDTO.MGVacationDTO vacation, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");		
		documentService.insertVacation(vacation,  userDTO.getUId());
	
		model.addAttribute("message", "작성이 완료되었습니다.");
		model.addAttribute("successUrl", "/myApprovalList");
		
		return "message";
	}
	
	
	/**
	 * 출장 보고서 데이터 저장
	 * @param model
	 * @param vacation
	 * @param session
	 * @return 작성 완료 메시지
	 */
	@PostMapping("/document/travel") 
	public String documentTravelInsertController(Model model, @ModelAttribute("travel") TravelDTO.MGVacationDTO travel, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");		
		documentService.insertTravel(travel,  userDTO.getUId());
	
		model.addAttribute("message", "작성이 완료되었습니다.");
		model.addAttribute("successUrl", "/myApprovalList");
		
		return "message";
	}

}
