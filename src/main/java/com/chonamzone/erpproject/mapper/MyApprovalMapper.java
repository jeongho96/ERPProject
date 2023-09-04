package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.MyApprovalDTO;

@Mapper
public interface MyApprovalMapper { 

	List<MyApprovalDTO> selectByIdAll();
	public void selectIdProceed(String id, String status); 
	MyApprovalDTO select(int dnum,int id);
    List<MyApprovalDTO> selectPaged(int start, int end, int id);
    
    int getTotalPosts(int id);

}
