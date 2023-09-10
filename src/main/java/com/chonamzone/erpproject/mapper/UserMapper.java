package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;

@Mapper
public interface UserMapper {
	// 회원 사번으로 이름 찾기
	String getNameById(int id);
	List<UserDTO> getAllUser();
	void insertUser(UserDTO user);
	UserDTO getUserById(int uId);
	void updatePwd(int uId, String uPwd);
	// 회원 사번으로 이름, 부서명 찾기
	UserDTO.MGResponse getUserWithPartnameById(int uId);
	// 회원 부서명, 이름으로 사번 찾기
	int getUserIdByName(@Param("pName") String pName, @Param("uName") String uName);
	
}
