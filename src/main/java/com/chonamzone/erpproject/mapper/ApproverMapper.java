package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.ApproverDTO;

@Mapper
public interface ApproverMapper {
	public List<ApproverDTO.Details> getApproverDetailsListByDSeq(int dSeq);
}
