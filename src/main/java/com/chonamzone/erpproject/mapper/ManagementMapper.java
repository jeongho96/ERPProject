package com.chonamzone.erpproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;


@Mapper
public interface ManagementMapper {
	public List<DocumentListDTO.MapperData> getManagementList(Map<String, Integer> pagination);
	public TravelDTO getManagementTravel(int dSeq);
	public VacationDTO getManagementVacation(int dSeq);
}
