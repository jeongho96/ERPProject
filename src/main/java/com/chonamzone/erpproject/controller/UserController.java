package com.chonamzone.erpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/encoder")
	@ResponseBody
	public String pwdEncoder(@RequestParam(value = "id") String uId) {
		int userId = Integer.parseInt(uId);
		UserDTO userDTO = userService.getUserById(userId);
		String encodePwd = userService.passwordEncoder().encode(userDTO.getUPwd());
		userService.updatePwd(userId, encodePwd);
		
		return "update complete";
	}
}
