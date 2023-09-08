package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;

@Mapper
public interface UserMapper {
	String getNameById(int id);
	
	void insertUser(UserDTO user);
	UserDTO getUserById(int uId);
	void updatePwd(int uId, String uPwd);
	UserDTO.MGResponse getUserWithPartnameById(int uId);
	
}
