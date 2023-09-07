package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.TravelDTO.MGVacationDTO;

@Mapper
public interface TravelMapper {
	TravelDTO getTravelByDSeq(int dSeq);
	void update(MGVacationDTO travel);
}
