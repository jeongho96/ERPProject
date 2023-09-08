package com.chonamzone.erpproject.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;

@Mapper
public interface VacationMapper {
	VacationDTO getVacationByDSeq(int dSeq);
	void update(MGVacationDTO vacation);
	void insert(VacationDTO.MGVacationDTO post);
	void insertDocList(int id);
	void insertApproval(int aOrderNum , int aApproverId, int dSeq);
}
