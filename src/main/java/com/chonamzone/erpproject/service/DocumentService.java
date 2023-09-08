package com.chonamzone.erpproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.DocumentListMapper;
import com.chonamzone.erpproject.mapper.PartnameMapper;
import com.chonamzone.erpproject.mapper.VacationMapper;
import com.chonamzone.erpproject.model.VacationDTO;

import lombok.RequiredArgsConstructor;

@Service  //서비스라고 적어줘야 서비스라고 알아들음!  //Been 등록 
@RequiredArgsConstructor
public class DocumentService {
	
	
	private final VacationMapper vacationMapper;
	private final PartnameMapper partnameMapper;
	
	
//	private DocumentListMapper documentListMapper;

	public void insert(VacationDTO.MGVacationDTO post, int loginId) {
		
		/*MyApproverDTO3 approverDTO3 = new MyApproverDTO3();*/
		System.out.println(loginId);
		vacationMapper.insertDocList(loginId);
		vacationMapper.insert(post);
		System.out.println(post.getDSeq());
		
//		
////		반드시 두개 선택해서 approval 입력시킴
//		vacationMapper.insertApproval(post.getAprvPa1(), post.getAprvName1(), post.getDSeq());
//		vacationMapper.insertApproval(post.getAprvPa2(), post.getAprvName2(), post.getDSeq());
//		void insertApproval(int aOrderNum , int aApproverId, int dSeq);
		
	}
	
	
	public String getPartname(int pId) {
		return partnameMapper.getPartnameByPId(pId);
	}

}
