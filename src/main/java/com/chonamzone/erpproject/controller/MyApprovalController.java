package com.chonamzone.erpproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonamzone.erpproject.model.MyApprovalDTO;
import com.chonamzone.erpproject.model.MyApprovalDTO2;
import com.chonamzone.erpproject.model.MyApprovalDTO3;
import com.chonamzone.erpproject.model.MyApprovalStateDO;
import com.chonamzone.erpproject.service.MyApprovalService;


@Controller
public class MyApprovalController {
	
	@Autowired
	private MyApprovalService myApprovalService;
	
	int loginid = 4;
	
	
	@GetMapping("/myApprovalList")
    public String myApprovalList(@RequestParam(defaultValue = "1") int page, Model model) {

		int perPage = 10; // 페이지당 항목 수

        List<MyApprovalDTO> posts = myApprovalService.getPaged(page, perPage, loginid);
        int totalPages = myApprovalService.getTotalPages(perPage, loginid);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "MyApprovalList";
    }
    
    @RequestMapping("/testdoc/{dSeq}")
    public String myApprovalDoc(@PathVariable int dSeq, Model model) {
    	
    	int check = 0;
    	//문서 정보 담기
    	MyApprovalDTO3 DrafterDto = myApprovalService.selectByDSeq(dSeq);
    		
    		
    	    	check = myApprovalService.nowApproval(dSeq, loginid, DrafterDto.getDDrafterId());
    	
    		List<MyApprovalDTO2> ADto = myApprovalService.selectByApprovers(dSeq);
    		model.addAttribute("approver", ADto);
    		model.addAttribute("mylist", DrafterDto);
    		model.addAttribute("check", check );
    	if(DrafterDto.getDCategory().equals("출장보고서")) {
    		//버케이션 정보 추가
    		
    		return "myApprovalTravel";
    		
    	}else {
    		
        return "myApprovalVacation";
    	}
    }
    
    @PostMapping("/dicision")
    public String getData(@ModelAttribute MyApprovalStateDO DO, Model model) {
        // Access dataObject.a, dataObject.b, and dataObject.c here
        String state = DO.getState();
        int dSeq = DO.getDSeq();
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
