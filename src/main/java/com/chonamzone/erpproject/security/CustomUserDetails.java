package com.chonamzone.erpproject.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chonamzone.erpproject.model.UserDTO;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private UserDTO userDTO;

    public CustomUserDetails(UserDTO user) {
        this.userDTO = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한을 반환 (권한 설정이 필요한 경우)
        return null;
    }

    @Override
    public String getPassword() {
        return userDTO.getUPwd(); 
    }

    @Override
    public String getUsername() {
    	return Integer.toString(userDTO.getUId());
    }

    // 아래의 메서드들을 구현해야 합니다. 예를 들어, 계정이 만료되었는지, 잠겼는지 등을 확인할 수 있습니다.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}