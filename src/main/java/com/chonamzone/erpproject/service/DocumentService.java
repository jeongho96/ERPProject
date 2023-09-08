package com.chonamzone.erpproject.service;

import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.ApproverMapper;
import com.chonamzone.erpproject.mapper.DocumentListMapper;
import com.chonamzone.erpproject.mapper.PartnameMapper;
import com.chonamzone.erpproject.mapper.VacationMapper;
import com.chonamzone.erpproject.model.ApproverDTO;
import com.chonamzone.erpproject.model.DocumentListDTO;
import com.chonamzone.erpproject.model.VacationDTO;

import lombok.RequiredArgsConstructor;

@Service  //서비스라고 적어줘야 서비스라고 알아들음!  //Been 등록 
@RequiredArgsConstructor
public class DocumentService {
	
	private final VacationMapper vacationMapper;
	private final PartnameMapper partnameMapper;
	private final DocumentListMapper documentListMapper;
	private final ApproverMapper approverMapper;
	
	public void insert(VacationDTO.MGVacationDTO post, int loginId) {
		DocumentListDTO documentList = new DocumentListDTO();
		documentList.setDDraftingDate(post.getDDraftingDate().toString());
		documentList.setDDrafterId(loginId);
		documentList.setDStatus("진행중");
		documentList.setDCategory("휴가신청서");
		
		documentListMapper.insert(documentList);
	
		System.out.println(documentList.getDSeq());
		
		post.setDSeq(documentList.getDSeq());
		vacationMapper.insert(post);
		
//		ApproverDTO approver = new ApproverDTO();
//		approver.setAOrderNum(1);
//		approver.setDSeq(documentList.getDSeq());
//		approver.setAApproverState("대기");
//		approverMapper.insert(post);
		
	}
	
	
	public String getPartname(int pId) {
		return partnameMapper.getPartnameByPId(pId);
	}

}
