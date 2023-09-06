package com.chonamzone.erpproject.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.ApproverMapper;
import com.chonamzone.erpproject.mapper.DocumentListMapper;
import com.chonamzone.erpproject.mapper.PartnameMapper;
import com.chonamzone.erpproject.mapper.TravelMapper;
import com.chonamzone.erpproject.mapper.UserMapper;
import com.chonamzone.erpproject.mapper.VacationMapper;
import com.chonamzone.erpproject.model.ApproverDTO;
import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.PartnameDTO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.UserDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagementService {
	
	private final DocumentListMapper  documentListMapper;
	private final ApproverMapper approverMapper; 
	private final TravelMapper travelMapper;
	private final VacationMapper vacationMapper;
	private final UserMapper userMapper;
	private final PartnameMapper partnameMapper;
	

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
	
	public VacationDTO.MGVacationDTO getManagementVacation(int dSeq) {
		DocumentListDTO.MapperData documentListDTO = documentListMapper.getDocumentListByDSeq(dSeq);
		List<ApproverDTO.MGResponse> approversDTO = approverMapper.getApproverDetailsListByDSeq(dSeq);
		VacationDTO vacationDTO = vacationMapper.getVacationByDSeq(dSeq);
		UserDTO.MGResponse userDTO = userMapper.getUserWithPartnameById(documentListDTO.getDDrafterId());
		
		VacationDTO.MGVacationDTO vacation = new VacationDTO.MGVacationDTO();
		vacation.setDSeq(documentListDTO.getDSeq());
		vacation.setDDraftingDate(documentListDTO.getDDraftingDate().toString());
		vacation.setAprvPa1(approversDTO.get(0).getPName());
		vacation.setAprvName1(approversDTO.get(0).getUName());
		vacation.setAprvPa2(approversDTO.get(1).getPName());
		vacation.setAprvName2(approversDTO.get(1).getUName());
		vacation.setPName(userDTO.getPName());
		vacation.setUName(userDTO.getUName());
		vacation.setUPosition(userDTO.getUPosition());
		vacation.setVLeaveType(vacationDTO.getVLeaveType());
		vacation.setVReason(vacationDTO.getVReason());
		vacation.setVStartDate(vacationDTO.getVStartDate().toString());
		vacation.setVEndDate(vacationDTO.getVEndDate().toString());
		vacation.setVEmployeeContact(vacationDTO.getVEmployeeContact());
		
		return vacation;
		
	}
	
	
	public List<PartnameDTO.MGResponse> getPartnameWithUsernameAll(){
		return partnameMapper.getPartnameWithUserNameAll();
	}


	public void updateVacations(MGVacationDTO vacation) {
		Map<String, Object> documentMap = new HashMap<>();
		documentMap.put("dSeq", vacation.getDSeq());
		documentMap.put("dDraftingDate", vacation.getDDraftingDate());
		
		documentListMapper.updateDDraftingDate(documentMap);
		
		Map<String, Object> aprvMap1 = new HashMap<>();
		aprvMap1.put("dSeq", vacation.getDSeq());
		aprvMap1.put("aOrderNum", 1);
		aprvMap1.put("pName", vacation.getAprvPa1());
		aprvMap1.put("uName", vacation.getAprvName1());
		
		approverMapper.updateApproverId(aprvMap1);
		
		Map<String, Object> aprvMap2 = new HashMap<>();
		aprvMap2.put("dSeq", vacation.getDSeq());
		aprvMap2.put("aOrderNum", 2);
		aprvMap2.put("pName", vacation.getAprvPa2());
		aprvMap2.put("uName", vacation.getAprvName2());
		
		approverMapper.updateApproverId(aprvMap2);
		
		Map<String, Object> vacationMap = new HashMap<>();
		vacationMap.put("dSeq", vacation.getDSeq());
		vacationMap.put("vLeaveType", vacation.getVLeaveType());
		vacationMap.put("vReason", vacation.getVReason());
		vacationMap.put("vStartDate", vacation.getVStartDate());
		vacationMap.put("vEndDate", vacation.getVEndDate());
		vacationMap.put("vEmployeeContact", vacation.getVEmployeeContact());
		
		vacationMapper.update(vacationMap);
		
	}

		
}
