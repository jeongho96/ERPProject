package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.MyApprovalDTO;
import com.chonamzone.erpproject.model.MyApprovalDTO2;

@Mapper
public interface MyApprovalMapper { 

	List<MyApprovalDTO> selectByIdAll();
	void selectIdProceed(String id, String status); 
	MyApprovalDTO select(int dSeq,int id);
    List<MyApprovalDTO> selectPaged(int start, int end, int id);
    MyApprovalDTO2 selectApprovers(int dSeq, int loginId);
    int getTotalPosts(int id);
    MyApprovalDTO2 selectOrder(int dSeq, int aOder);
    void updateApproversState(int dSeq, int id, String state);
    void updateDocStatus(int dSeq, String state);
}
