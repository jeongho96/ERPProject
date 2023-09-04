package com.chonamzone.erpproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.ApproversDTO;
import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;


@Mapper
public interface ManagementMapper {
	public List<DocumentListDTO.MapperData> getManagementList(Map<String, Integer> pagination);
	public TravelDTO getTravelByDSeq(int dSeq);
	public VacationDTO getVacationByDSeq(int dSeq);
	public List<ApproversDTO.Details> getApproverDetailsListByDSeq(int dSeq);
}
