package com.chonamzone.erpproject.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chonamzone.erpproject.model.UserDTO;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private UserDTO userDTO;
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

    public CustomUserDetails(UserDTO user) {
        this.userDTO = user;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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