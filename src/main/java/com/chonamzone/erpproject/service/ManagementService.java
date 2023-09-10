package com.chonamzone.erpproject.service;

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
import com.chonamzone.erpproject.model.DocumentListDTO.MGResponse;
import com.chonamzone.erpproject.model.DocumentListDTO.MapperData;
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
	
	/**
	 * 통합 결재 관리 전체 데이터 출력 메소드
	 * 페이지 번호에 따라 데이터 갯수 달라짐(pagination)
	 * @param page
	 * @return 전체 결재문서 데이터 리스트
	 */
	public List<DocumentListDTO.MGResponse> getManagementAllList(int page) {
		Map<String, Integer> pagination = new HashMap<>();
		
		// ROWNUM은 1부터 시작
		pagination.put("startPage", (page-1)*10+1);
		pagination.put("endPage", page*10);

		List<DocumentListDTO.MapperData> documentMapperList = documentListMapper.getManagementAllList(pagination);
		return createResponseList(documentMapperList);
	}
	
	/**
	 * 통합 결재 관리에서 진행중, 반려, 승인 탭 데이터 출력 메소드
	 * 페이지 번호에 따라 데이터 갯수 달라짐(pagination)
	 * @param page
	 * @param status
	 * @return 문서 상태에 따른 결재문서 데이터 리스트
	 */
	public List<DocumentListDTO.MGResponse> getManagementStatusList(int page, String status) {
		Map<String, Object> map = new HashMap<>();

		map.put("startPage", (page-1)*10+1);
		map.put("endPage", page*10);
		map.put("status", status);

		List<DocumentListDTO.MapperData> documentMapperList = documentListMapper.getManagementList(map);
		
		return createResponseList(documentMapperList);
	}
	
	
	/**
	 * 통합 결재 관리 리스트에서 reponseList 만드는 함수
	 * 1. 통합 결재 관리 리스트에 들어갈 정보들 documentResponseList에 옮겨 담음
	 * 2. 리스트별 기안자 사번->이름, 결재자 사번->이름으로 변경함 
	 * @param documentMapperList
	 * @return 결재 문서 목록 리스트
	 */
	private List<MGResponse> createResponseList(List<MapperData> documentMapperList) {
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

	
	/**
	 * 통합 결재 관리 중 출장보고서 상세보기
	 * @param dSeq
	 * @return 출장보고서 데이터
	 */
	public TravelDTO.MGVacationDTO getManagementTravel(int dSeq) {
		DocumentListDTO.MapperData documentListDTO = documentListMapper.getDocumentListByDSeq(dSeq);
		List<ApproverDTO.MGResponse> approversDTO = approverMapper.getApproverDetailsListByDSeq(dSeq);
		TravelDTO travelDTO = travelMapper.getTravelByDSeq(dSeq);
		UserDTO.MGResponse userDTO = userMapper.getUserWithPartnameById(documentListDTO.getDDrafterId());
		
		TravelDTO.MGVacationDTO travel = new TravelDTO.MGVacationDTO();
		travel.setDSeq(documentListDTO.getDSeq());
		travel.setDDraftingDate(documentListDTO.getDDraftingDate().toString());
		travel.setAprvPa1(approversDTO.get(0).getPName());
		travel.setAprvName1(approversDTO.get(0).getUName());
		travel.setAprvPa2(approversDTO.get(1).getPName());
		travel.setAprvName2(approversDTO.get(1).getUName());
		travel.setPName(userDTO.getPName());
		travel.setUName(userDTO.getUName());
		travel.setUPosition(userDTO.getUPosition());
		travel.setTLocation(travelDTO.getTLocation());
		travel.setTReason(travelDTO.getTReason());
		travel.setTAccommodation(travelDTO.getTAccommodation());
		travel.setTTransCost(travelDTO.getTTransCost());
		travel.setTFoodCost(travelDTO.getTFoodCost());
		travel.setTAccommodationCost(travelDTO.getTAccommodationCost());
		travel.setTEtcCost(travelDTO.getTEtcCost());
		travel.setTStartDate(travelDTO.getTStartDate().toString());
		travel.setTEndDate(travelDTO.getTEndDate().toString());
		
		return travel;
	}
	
	
	/**
	 * 통합 결재 관리 중 휴가신청서 상세보기
	 * @param dSeq
	 * @return 휴가신청서 데이터
	 */
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

	
	/**
	 * 모든 부서와 부서에 따른 사원 정보 가져오는 함수
	 * @return 부서명, 해당 사원명 전체 리스트 
	 */
	public List<PartnameDTO.MGResponse> getPartnameWithUsernameAll(){
		return partnameMapper.getPartnameWithUserNameAll();
	}

	
	/**
	 * 휴가신청서 정보 수정
	 * @param vacation
	 */
	public void updateVacations(VacationDTO.MGVacationDTO vacation) {		
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
		vacationMapper.update(vacation);
	}
	
	
	/**
	 * 출장보고서 정보 수정
	 * @param travel
	 */
	public void updateTravels(TravelDTO.MGVacationDTO travel) {
		Map<String, Object> documentMap = new HashMap<>();
		documentMap.put("dSeq", travel.getDSeq());
		documentMap.put("dDraftingDate", travel.getDDraftingDate());
		
		documentListMapper.updateDDraftingDate(documentMap);
		
		Map<String, Object> aprvMap1 = new HashMap<>();
		aprvMap1.put("dSeq", travel.getDSeq());
		aprvMap1.put("aOrderNum", 1);
		aprvMap1.put("pName", travel.getAprvPa1());
		aprvMap1.put("uName", travel.getAprvName1());
		
		approverMapper.updateApproverId(aprvMap1);
		
		Map<String, Object> aprvMap2 = new HashMap<>();
		aprvMap2.put("dSeq", travel.getDSeq());
		aprvMap2.put("aOrderNum", 2);
		aprvMap2.put("pName", travel.getAprvPa2());
		aprvMap2.put("uName", travel.getAprvName2());
		
		approverMapper.updateApproverId(aprvMap2);
		travelMapper.update(travel);
	}
	
		
}
