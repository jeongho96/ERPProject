package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.model.TravelDTO.MGVacationDTO;

@Mapper
public interface TravelMapper {
	// 문서번호(dSeq) 이용해 출장보고서 데이터 찾기
	TravelDTO getTravelByDSeq(int dSeq);
	// 출장보고서 데이터 수정
	void update(TravelDTO.MGVacationDTO travel);
	// 출장보고서 데이터 저장
	void insert(TravelDTO.MGVacationDTO post);
	
}
