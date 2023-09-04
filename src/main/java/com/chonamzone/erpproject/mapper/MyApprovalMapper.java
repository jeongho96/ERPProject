package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chonamzone.erpproject.model.MyApprovalDto;

@Mapper
public interface MyApprovalMapper { 

	List<MyApprovalDto> selectByIdAll();
	public void selectIdProceed(String id, String status); 
	MyApprovalDto select(int dnum,int id);
    List<MyApprovalDto> selectPaged(int start, int end, int id);
    
    int getTotalPosts(int id);

}
