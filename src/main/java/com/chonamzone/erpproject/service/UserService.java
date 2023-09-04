package com.chonamzone.erpproject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.UserMapper;
import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	
	
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public UserDTO create(String username, String email, String password) {
		UserDTO user = new UserDTO();
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode(password));
//        this.userRepository.save(user);
        return user;
    }
	
	public UserDTO getUserById(int id) {
        return userMapper.getUserById(id);
    }
	
	public void updatePwd(int id, String uPwd) {
		
		userMapper.updatePwd(id, uPwd);
	}


	public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
	
	@Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 사용자 데이터베이스에서 사용자 정보를 검색하여 UserDetails 객체로 반환
        UserDTO user = userMapper.getUserById(Integer.parseInt(id));
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + id);
        }
        return new CustomUserDetails(user); // CustomUserDetails는 User 클래스를 기반으로 한 사용자 정의 UserDetails 구현체입니다.
    }
}
