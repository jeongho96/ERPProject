package com.chonamzone.erpproject.service;

import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.ApproverMapper;
import com.chonamzone.erpproject.mapper.DocumentListMapper;
import com.chonamzone.erpproject.mapper.PartnameMapper;
import com.chonamzone.erpproject.mapper.TravelMapper;
import com.chonamzone.erpproject.mapper.UserMapper;
import com.chonamzone.erpproject.mapper.VacationMapper;
import com.chonamzone.erpproject.model.ApproverDTO;
import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.TravelDTO;
import com.chonamzone.erpproject.model.VacationDTO;

import lombok.RequiredArgsConstructor;

@Service  //서비스라고 적어줘야 서비스라고 알아들음!  //Been 등록 
@RequiredArgsConstructor
public class DocumentService {
	
	private final VacationMapper vacationMapper;
	private final PartnameMapper partnameMapper;
	private final DocumentListMapper documentListMapper;
	private final ApproverMapper approverMapper;
	private final UserMapper userMapper;
	private final TravelMapper travelMapper;
	
	
	/**
	 * 휴가 신청서 각 테이블 별 데이터 저장
	 * @param post
	 * @param loginId
	 */
	public void insertVacation(VacationDTO.MGVacationDTO post, int loginId) {
		DocumentListDTO documentList = new DocumentListDTO();
		documentList.setDDraftingDate(post.getDDraftingDate().toString());
		documentList.setDDrafterId(loginId);
		documentList.setDStatus("진행중");
		documentList.setDCategory("휴가신청서");
		
		documentListMapper.insert(documentList);
	
		post.setDSeq(documentList.getDSeq());
		vacationMapper.insert(post);
		
		ApproverDTO approver1 = new ApproverDTO();
		approver1.setAOrderNum(1);
		approver1.setDSeq(documentList.getDSeq());
		approver1.setAApproverId(userMapper.getUserIdByName(post.getAprvPa1(), post.getAprvName1()));
		approver1.setAApproverState("대기");
		approverMapper.insert(approver1);
		
		ApproverDTO approver2 = new ApproverDTO();
		approver2.setAOrderNum(2);
		approver2.setDSeq(documentList.getDSeq());
		approver2.setAApproverId(userMapper.getUserIdByName(post.getAprvPa2(), post.getAprvName2()));
		approver2.setAApproverState("대기");
		approverMapper.insert(approver2);
		
	}
	
	/**
	 * 출장 보고서 각 테이블 별 데이터 저장
	 * @param post
	 * @param loginId
	 */
	public void insertTravel(TravelDTO.MGVacationDTO post, int loginId) {
		DocumentListDTO documentList = new DocumentListDTO();
		documentList.setDDraftingDate(post.getDDraftingDate().toString());
		documentList.setDDrafterId(loginId);
		documentList.setDStatus("진행중");
		documentList.setDCategory("출장보고서");
		
		documentListMapper.insert(documentList);
	
		post.setDSeq(documentList.getDSeq());
		travelMapper.insert(post);
		
		ApproverDTO approver1 = new ApproverDTO();
		approver1.setAOrderNum(1);
		approver1.setDSeq(documentList.getDSeq());
		approver1.setAApproverId(userMapper.getUserIdByName(post.getAprvPa1(), post.getAprvName1()));
		approver1.setAApproverState("대기");
		approverMapper.insert(approver1);
		
		ApproverDTO approver2 = new ApproverDTO();
		approver2.setAOrderNum(2);
		approver2.setDSeq(documentList.getDSeq());
		approver2.setAApproverId(userMapper.getUserIdByName(post.getAprvPa2(), post.getAprvName2()));
		approver2.setAApproverState("대기");
		approverMapper.insert(approver2);
		
	}
	
	
	/**
	 * 로그인한 직원 부서명 출력하는 메소드
	 * @param pId
	 * @return 로그인한 직원 부서명
	 */
	public String getPartname(int pId) {
		return partnameMapper.getPartnameByPId(pId);
	}
	

}
