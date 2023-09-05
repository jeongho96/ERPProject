package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.TravelDTO;

@Mapper
public interface TravelMapper {
	public TravelDTO getTravelByDSeq(int dSeq);
}
