package com.chonamzone.erpproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonamzone.erpproject.model.MyApprovalDTO;
import com.chonamzone.erpproject.model.MyApprovalDTO2;
import com.chonamzone.erpproject.model.MyApprovalDTO3;
import com.chonamzone.erpproject.model.MyApprovalStateDO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;
import com.chonamzone.erpproject.service.ManagementService;
import com.chonamzone.erpproject.service.MyApprovalService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MyApprovalController {
	
	private final MyApprovalService myApprovalService;
	private final ManagementService managementService;
	
	
	@GetMapping("/myApprovalList")
    public String myApprovalList(@RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {
		
		UserDTO userDTO= (UserDTO)session.getAttribute("loginUser");
		int loginid = userDTO.getUId();
		
		int perPage = 10; // 페이지당 항목 수

        List<MyApprovalDTO> posts = myApprovalService.getPaged(page, perPage, loginid);
        int totalPages = myApprovalService.getTotalPages(perPage, loginid);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "myApprovalList";
    }
    
	@GetMapping("/myApprovaldoc/{dSeq}")
    public String myApprovalDoc(@PathVariable int dSeq, Model model, HttpSession session) {
		UserDTO userDTO= (UserDTO)session.getAttribute("loginUser");
		int loginid = userDTO.getUId();
    	
    	int check = 0;
    	//문서 정보 담기
    	MyApprovalDTO3 DrafterDto = myApprovalService.selectByDSeq(dSeq);
    		
    	    check = myApprovalService.nowApproval(dSeq, loginid, DrafterDto.getDDrafterId());

    		model.addAttribute("mylist", DrafterDto);
    		model.addAttribute("check", check );

    	if(DrafterDto.getDCategory().equals("출장보고서")) {
    	
    		model.addAttribute("travel", managementService.getManagementTravel(dSeq));
    		model.addAttribute("partnames", managementService.getPartnameWithUsernameAll());
    		return "myApprovalTravel";
    		
    	}else {
 
    		model.addAttribute("vacation", managementService.getManagementVacation(dSeq) );
    		model.addAttribute("partnames", managementService.getPartnameWithUsernameAll());
        return "myApprovalVacation";
    	}
    }
    
    @PutMapping("/dicision")
    public String getData(@ModelAttribute MyApprovalStateDO DO, Model model, HttpSession session) {
        
		UserDTO userDTO= (UserDTO)session.getAttribute("loginUser");
		int loginid = userDTO.getUId();
        String state = DO.getState();
        int dSeq = DO.getDSeq();
        System.out.println(state);
        System.out.println(dSeq);
		MyApprovalDTO3 DrafterDto = myApprovalService.selectByDSeq(dSeq);
		System.out.println(state);
        myApprovalService.approvalState(state, dSeq, loginid);
		
		if(state.equals("결재취소")){
		return "myApprovalList";
		}else if(DrafterDto.getDCategory().equals("출장보고서")){
		//리 다이렉트나 출장보고서 정보 담아서 보내기
		//model.addAttribute("travelInfo", tInfo );
		  return "redirect:/testdoc/" + dSeq;
		}else{
		//리 다이렉트나 휴가신청서 정보 담아서 보내기
		 //model.addAttribute("vacationInfo", vInfo );
			return "redirect:/testdoc/" + dSeq;
      	}
        
    }
   
    
    
    

}
