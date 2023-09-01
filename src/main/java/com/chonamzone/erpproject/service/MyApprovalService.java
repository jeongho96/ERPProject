package com.chonamzone.erpproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chonamzone.erpproject.mapper.MyApprovalMapper;
import com.chonamzone.erpproject.model.MyApprovalDto;

@Service

public class MyApprovalService {
	
	private final MyApprovalMapper myapprovalmapper;

	@Autowired
	public MyApprovalService(MyApprovalMapper myapprovalmapper) {
		this.myapprovalmapper = myapprovalmapper;
	}
	
    public List<MyApprovalDto> getAll() {
        return myapprovalmapper.selectByIdAll();
    }

    public List<MyApprovalDto> getPaged(int page, int perPage) {
        int start = (page - 1) * perPage;
        int end = start + perPage;
        return myapprovalmapper.selectPaged(start, end);
    }
	
	
}
