package com.chonamzone.erpproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.PartnameMapper;
import com.chonamzone.erpproject.mapper.UserMapper;
import com.chonamzone.erpproject.model.PartnameDTO;
import com.chonamzone.erpproject.model.UserDTO;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	
	private final UserMapper userMapper;
	private final PartnameMapper partnameMapper;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	public UserDTO getUserById(String id) {
		int uId = Integer.parseInt(id);
        return userMapper.getUserById(uId);
    }

	public List<PartnameDTO> getAllPartNames() {
		
		return partnameMapper.getAllPartNames();
	}
	
	public List<UserDTO> getAllUser(){
		return userMapper.getAllUser();
	}
	
	public void registerUser(UserDTO user) {
        userMapper.insertUser(user);
    }
	
	public void updatePwd(int id, String uPwd) {
		userMapper.updatePwd(id, uPwd);
	}
	
	
	public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
	
}
