package com.chonamzone.erpproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.MyApprovalMapper;
import com.chonamzone.erpproject.model.MyApprovalDTO;
import com.chonamzone.erpproject.model.MyApprovalDTO2;

@Service

public class MyApprovalService {
	
	private final MyApprovalMapper myapprovalmapper;

	@Autowired
	public MyApprovalService(MyApprovalMapper myapprovalmapper) {
		this.myapprovalmapper = myapprovalmapper;
	}
	
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
    
    public MyApprovalDTO select(int dnum, int loginId) {
    	MyApprovalDTO dselect = myapprovalmapper.select(dnum, loginId); 
    	return dselect;
    }
    
    public int nowApproval(int dSeq, int loginId) {
    	
    	int check = 1; //1은 결재/반려 표시, 0은 표시안함, 2는 결재취소 표시
    	MyApprovalDTO Dto = myapprovalmapper.select(dSeq, loginId);
    	
    	MyApprovalDTO2 loginDto = myapprovalmapper.selectApprovers(dSeq, loginId);
    	
    		if(Dto.getDStatus() != "진행중" || Dto.getAApproverId() == loginId ||(
    				loginDto.getAOder() == 2 && myapprovalmapper.selectOrder(dSeq, 1).getAApproverState() != "승인")) {
    			check = 0;
    		}else if(Dto.getDStatus() == "진행중" && Dto.getAApproverId() == loginId
    				&& myapprovalmapper.selectOrder(dSeq, 1).getAApproverState() == "진행중"){
    		}

    	return check;
    }
    
    public void approvalState(String state, MyApprovalDTO2 Dto) {
    	//state는 어떤 버튼을 눌렀는가
    	int id = Dto.getAApproverId();
    	int dSeq = Dto.getDSeq();
    if(state == "반려") {
    	myapprovalmapper.updateApproversState(dSeq, id, "반려");
    	myapprovalmapper.updateDocStatus(dSeq, "반려");
    }else if(Dto.getAOder() == 2) {
    	myapprovalmapper.updateApproversState(dSeq, id, "승인");
    	myapprovalmapper.updateDocStatus(dSeq, "최종승인");
    }else {
    	myapprovalmapper.updateApproversState(dSeq, id, "승인");
    }
    	
    }
}
