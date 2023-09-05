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
    
    public int nowApproval(MyApprovalDTO Dto, int loginId) {
    	
    	int check = 1;
    	int dSeq = Dto.getDSeq();
    	MyApprovalDTO2 loginDto = myapprovalmapper.selectApprovers(dSeq, loginId);
  
    		if(Dto.getDStatus() != "진행중" || Dto.getAApproverId() == loginId ||(
    				loginDto.getAOder() == 2 && myapprovalmapper.selectOder(dSeq, 1).getAApproverState() != "승인")) {
    			check = 0;
    		}
 
    		
    		
    	return check;
    }
	
}
