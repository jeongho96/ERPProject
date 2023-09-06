package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.TravelDTO;

@Mapper
public interface TravelMapper {
	TravelDTO getTravelByDSeq(int dSeq);
}
