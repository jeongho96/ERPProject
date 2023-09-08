package com.chonamzone.erpproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.service.DocumentService;

@Controller
public class DocumentController {

	@Autowired // 의존성 주입 객체 자동 생성 기능.
	private DocumentService documentService;
	
	
	@GetMapping("/document")
	public String documentcontroller() {
		return"document";
	}
	
	@GetMapping("/document/vacation")
	public String documentvacationcontroller(Model model, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");
		
		VacationDTO.MGVacationDTO vacation = new VacationDTO.MGVacationDTO();
		vacation.setUserData(userDTO);
		vacation.setPName(documentService.getPartname(userDTO.getPId()));
		
		model.addAttribute("vacation", vacation);
		return "documentVacation";
	}
	
	
	@GetMapping("/document/travel")
	public String documenttravelcontroller() {
		return "documentTravel";
	}
	
	
	@PostMapping("/document/vacation") 
	public String documentVacationInsertController(@ModelAttribute("vacation") VacationDTO.MGVacationDTO vacation,HttpSession session ) {
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");		
		documentService.insert(vacation,  userDTO.getUId());
	
		return "documentVacation";
	}

}
