package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.VacationDTO;

@Mapper
public interface VacationMapper {
	VacationDTO getVacationByDSeq(int dSeq);
}
