package com.chonamzone.erpproject.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;

@Mapper
public interface VacationMapper {
	// 문서 번호 이용해 휴가신청서 찾기
	VacationDTO getVacationByDSeq(int dSeq);
	// 휴가신청서 데이터 수정
	void update(MGVacationDTO vacation);
	// 휴가신청서 데이터 저장
	void insert(VacationDTO.MGVacationDTO post);
}
