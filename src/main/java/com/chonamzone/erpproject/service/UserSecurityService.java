package com.chonamzone.erpproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.UserMapper;
import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.UserRole;
import com.chonamzone.erpproject.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserMapper userMapper;

	@Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 사용자 데이터베이스에서 사용자 정보를 검색하여 UserDetails 객체로 반환
        UserDTO user = userMapper.getUserById(Integer.parseInt(id));

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + id);
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        if (3 == user.getPId()) {
        	authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
     // CustomUserDetails 객체 생성 및 권한 설정
        CustomUserDetails userDetails = new CustomUserDetails(user);
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
