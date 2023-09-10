package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.PartnameDTO;

@Mapper
public interface PartnameMapper {
	// 전체 부서명과 해당 부서 직원명 출력
	List<PartnameDTO.MGResponse> getPartnameWithUserNameAll();
	List<PartnameDTO> getAllPartNames();
	// 부서id(pId)를 이용해 부서명 출력
	String getPartnameByPId(int pId);
}
