package com.chonamzone.erpproject.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chonamzone.erpproject.model.PartnameDTO;
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
	
	@GetMapping("/access-denied")
	  public String showAccessDeniedPage() {
	    return "accessDenied";
	  }
	
	@GetMapping("/home")
	public String index(HttpSession session, Model model) {
		
		session.setAttribute("loginUser", userService.getUserById((String)session.getAttribute("loginUserId")));
		return "index";
	}
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
		List<PartnameDTO> partNames = userService.getAllPartNames();
		model.addAttribute("partList", partNames);

        return "registerForm";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") UserDTO user) {
    	
    	String defaultPwd = user.getUHireDate().replaceAll("-", "");
    	user.setUPwd(userService.passwordEncoder().encode(defaultPwd));

        userService.registerUser(user);
        return "registerSuccess";
    }
	
	
	@GetMapping("/mypage")
	public String myPage() {
		return "mypage";
	}
	
	@PostMapping("/mypage")
	public String myPage(HttpSession session, @RequestParam("newPassword") String newPwd) {
		// 1. 현재 로그인 유저의 비밀번호를 가져온다.(1번 입력 비교용) getmapping에서 가져옴.
		// 2. 로그인 유저의 비밀번호와 1번 입력 값이 달라서는 안된다. javascript
		// 3. 현재 계정 정보를 가져온다.
		UserDTO userDTO = (UserDTO)session.getAttribute("loginUser");
		// 4. 현재 계정의 비밀번호에 새 비밀번호를 암호화해서 update한다.
		String encodePwd = userService.passwordEncoder().encode(newPwd);
		userService.updatePwd(userDTO.getUId(), encodePwd);
		// 조건 문 배제.
		return "mypageAlert";
	}
	
}
