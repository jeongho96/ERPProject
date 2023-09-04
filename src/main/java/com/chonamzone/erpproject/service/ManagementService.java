package com.chonamzone.erpproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.ManagementMapper;
import com.chonamzone.erpproject.mapper.UserMapper;
import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagementService {
	
	private final ManagementMapper managementMapper;
	private final UserMapper userMapper;
	

	public List<DocumentListDTO.Response> getManagementList(int page) {
		Map<String, Integer> pagination = new HashMap<>();
		
		// ROWNUM은 1부터 시작
		pagination.put("startPage", (page-1)*10+1);
		pagination.put("endPage", page*10);

		List<DocumentListDTO.MapperData> documentMapperList = managementMapper.getManagementList(pagination);
		List<DocumentListDTO.Response> documentResponseList = new ArrayList<>();
		
		
		for(DocumentListDTO.MapperData documentDTO : documentMapperList) {
			DocumentListDTO.Response documentResponse = new DocumentListDTO.Response(documentDTO);
			
			String drafterName = userMapper.getNameById(documentDTO.getDDrafterId());
			String approverName = userMapper.getNameById(documentDTO.getAApproverId());
			documentResponse.idToName(drafterName, approverName);
			
			documentResponseList.add(documentResponse);

		}
		
		return documentResponseList;
	}
	
	
	public TravelDTO getManagementTravel(int dSeq) {
		return managementMapper.getManagementTravel(dSeq);
	}
	
	public VacationDTO getManagementVacation(int dSeq) {
		return managementMapper.getManagementVacation(dSeq);
	}

		
}
