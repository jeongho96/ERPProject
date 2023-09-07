package com.chonamzone.erpproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.ApproverDTO;
import com.chonamzone.erpproject.model.VacationDTO;

@Mapper
public interface ApproverMapper {
	List<ApproverDTO.MGResponse> getApproverDetailsListByDSeq(int dSeq);
	void updateApproverId(Map<String, Object> aprvMap1);
}
