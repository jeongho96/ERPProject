package com.chonamzone.erpproject.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;

@Mapper
public interface VacationMapper {
	VacationDTO getVacationByDSeq(int dSeq);
	void updateVacation(MGVacationDTO vacation);
	void update(Map<String, Object> vacationMap);
}
