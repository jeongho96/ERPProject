package com.chonamzone.erpproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.MyApprovalMapper;
import com.chonamzone.erpproject.model.MyApprovalDTO;
import com.chonamzone.erpproject.model.MyApprovalDTO2;
import com.chonamzone.erpproject.model.MyApprovalDTO3;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyApprovalService {
	
	private final MyApprovalMapper myapprovalmapper;


	
    public List<MyApprovalDTO> getAll() {
        return myapprovalmapper.selectByIdAll();
    }

    public List<MyApprovalDTO> getPaged(int page, int perPage, int loginid) {
        int start = (page - 1) * perPage;
        int end = start + perPage;
        List<MyApprovalDTO> MADto = myapprovalmapper.selectPaged(start, end, loginid);
        return MADto;
    }
    
    public int getTotalPages(int perPage, int loginId) {
        int totalPosts = myapprovalmapper.getTotalPosts(loginId); // Implement this method in the mapper
        int totalPages = (int) Math.ceil((double) totalPosts / perPage);
        
        return totalPages;
    }
    
    public MyApprovalDTO selectByApprover(int dSeq, int loginId) {
    	MyApprovalDTO selectByApprover = myapprovalmapper.selectByApprover(dSeq, loginId); 
    	return selectByApprover;
    }
    
    
    public int nowApproval(int dSeq, int loginId, int drafterId) {
    	//결재자인지 확인한 후 이 매서드 사용하고 있음
    	int check = 0; //1은 결재/반려 표시, 0은 표시안함, 2는 결재취소
    	//문서정보
    	MyApprovalDTO Dto = myapprovalmapper.selectByApprover(dSeq, loginId);
    	//내 approval 정보(로그인한 사람)
    	MyApprovalDTO2 loginDto = myapprovalmapper.selectApprovers(dSeq, loginId);
    	
    	//순수 문서정보만
    	MyApprovalDTO3 DrafterDto = myapprovalmapper.selectByDSeq(dSeq);
	
    	
		if(DrafterDto.getDDrafterId() != loginId) {//결재자라면

    		if(Dto.getDStatus().equals
    			("진행중") && (((loginDto.getAOrderNum() == 1)&&(myapprovalmapper.selectOrder(dSeq, 1).getAApproverState().equals("대기")))||
    				(loginDto.getAOrderNum() == 2 && myapprovalmapper.selectOrder(dSeq, 1).getAApproverState().equals("승인")))) {
    			check = 1;
    			}
    		
		}else if(myapprovalmapper.selectOrder(dSeq, 1).getAApproverState().equals("대기")) {
				check = 2;
			}else {
				check = 1;
			}
		
    	return check;
    }
    
    public MyApprovalDTO3 selectByDSeq(int dSeq) {
    	return myapprovalmapper.selectByDSeq(dSeq);
    }
    
    //public void approvalState(String state, MyApprovalDTO2 Dto) {
    public void approvalState(String state, int dSeq, int loginId) {
    	//state는 결재/반려 중 어떤 버튼을 눌렀는지
    	
    	
    	
    	MyApprovalDTO2 Dto = myapprovalmapper.selectApprovers(dSeq, loginId);
 
    	
    //if(state.equals("결재취소")) {    }else
    if(state.equals("반려")) {
    	myapprovalmapper.updateApproversState(dSeq, loginId, "반려");
    	myapprovalmapper.updateDocStatus(dSeq, "반려");
    }else if(Dto.getAOrderNum() == 2) {
    	myapprovalmapper.updateApproversState(dSeq, loginId, "승인");
    	myapprovalmapper.updateDocStatus(dSeq, "최종승인");
    }else {
    	myapprovalmapper.updateApproversState(dSeq, loginId, "승인");
    }
    	
    }
    
    public List<MyApprovalDTO2> selectByApprovers(int dSeq){
    	return myapprovalmapper.selectApproverByDSeq(dSeq);
    }
}
