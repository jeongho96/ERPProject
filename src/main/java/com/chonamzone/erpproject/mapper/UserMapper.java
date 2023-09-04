package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.UserDTO;

@Mapper
public interface UserMapper {
	public String getNameById(int id);
	
	// 로그인
	public UserDTO getUserById(int uId);
	public void updatePwd(int uId, String uPwd);
}
