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
	
	
	@GetMapping("/myApprovalList")
    public String myApprovalList(@RequestParam(defaultValue = "1") int page, Model model) {
        int perPage = 10; // 페이지당 게시물 수
        List<MyApprovalDto> mylist = myApprovalService.getPaged(page, perPage);
        model.addAttribute("mylist", mylist);
        return "list";
    }

}
