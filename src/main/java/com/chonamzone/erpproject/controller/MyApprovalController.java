package com.chonamzone.erpproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonamzone.erpproject.model.MyApprovalDto;
import com.chonamzone.erpproject.service.MyApprovalService;


@Controller
public class MyApprovalController {
	
	@Autowired
	private MyApprovalService myApprovalService;
	
	int loginid = 4;
	
	
	@GetMapping("/list")
    public String myApprovalList(@RequestParam(defaultValue = "1") int page, Model model) {

		int perPage = 10; // 페이지당 항목 수

        List<MyApprovalDto> posts = myApprovalService.getPaged(page, perPage, loginid);
        int totalPages = myApprovalService.getTotalPages(perPage, loginid);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        
        List<MyApprovalDto> mylist = myApprovalService.getPaged(page, perPage, loginid);
        model.addAttribute("mylist", mylist);
		
        return "list";
    }
    
    @GetMapping("/testdoc")
    public String myApprovalDoc(@RequestParam int dnum, Model model) {

    	MyApprovalDto Dto = myApprovalService.select(dnum, loginid);
    	
    	model.addAttribute("mylist", Dto);
    	
        return "testdoc";
    }

}
