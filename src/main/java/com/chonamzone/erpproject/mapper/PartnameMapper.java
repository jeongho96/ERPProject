package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.PartnameDTO;

@Mapper
public interface PartnameMapper {
	List<PartnameDTO.MGResponse> getPartnameWithUserNameAll();
	List<PartnameDTO> getAllPartNames();
	String getPartnameByPId(int pId);
}
