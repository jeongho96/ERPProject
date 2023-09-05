package com.chonamzone.erpproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.ApproverMapper;
import com.chonamzone.erpproject.mapper.DocumentListMapper;
import com.chonamzone.erpproject.mapper.TravelMapper;
import com.chonamzone.erpproject.mapper.UserMapper;
import com.chonamzone.erpproject.mapper.VacationMapper;
import com.chonamzone.erpproject.model.ApproverDTO;
import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.VacationDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagementService {
	
	private final DocumentListMapper  documentListMapper;
	private final ApproverMapper approverMapper; 
	private final TravelMapper travelMapper;
	private final VacationMapper vacationMapper;
	private final UserMapper userMapper;
	

	public List<DocumentListDTO.MGResponse> getManagementList(int page) {
		Map<String, Integer> pagination = new HashMap<>();
		
		// ROWNUM은 1부터 시작
		pagination.put("startPage", (page-1)*10+1);
		pagination.put("endPage", page*10);

		List<DocumentListDTO.MapperData> documentMapperList = documentListMapper.getManagementList(pagination);
		List<DocumentListDTO.MGResponse> documentResponseList = new ArrayList<>();
		
		
		for(DocumentListDTO.MapperData documentDTO : documentMapperList) {
			DocumentListDTO.MGResponse documentResponse = new DocumentListDTO.MGResponse(documentDTO);
			
			String drafterName = userMapper.getNameById(documentDTO.getDDrafterId());
			String approverName = userMapper.getNameById(documentDTO.getAApproverId());
			documentResponse.idToName(drafterName, approverName);
			
			documentResponseList.add(documentResponse);

		}
		
		return documentResponseList;
	}
	
	
	public TravelDTO getManagementTravel(int dSeq) {
		return travelMapper.getTravelByDSeq(dSeq);
	}
	
	public Map<String, Object> getManagementVacation(int dSeq) {
		Map<String, Object> map = new HashMap<>();
		
		DocumentListDTO.MapperData documentListDTO = documentListMapper.getDocumentListByDSeq(dSeq);
		List<ApproverDTO.MGResponse> approverList = approverMapper.getApproverDetailsListByDSeq(dSeq);
		VacationDTO vacationDTO = vacationMapper.getVacationByDSeq(dSeq);
		UserDTO.MGResponse userDTO = userMapper.getUserWithPartnameById(documentListDTO.getDDrafterId());
		
		map.put("documentListDTO", documentListDTO);
		map.put("approverList", approverList);
		map.put("vacationDTO", vacationDTO);
		map.put("userDTO", userDTO); 
		
		return map;
		
	}

		
}
