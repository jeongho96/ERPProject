package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.UserDTO;

@Mapper
public interface UserMapper {
	String getNameById(int id);
	
	// 로그인
	UserDTO getUserById(int uId);
	void updatePwd(int uId, String uPwd);
	
	UserDTO.MGResponse getUserWithPartnameById(int uId);
}
