package com.chonamzone.erpproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.MyApprovalDTO;
import com.chonamzone.erpproject.model.MyApprovalDTO2;
import com.chonamzone.erpproject.model.MyApprovalDTO3;

@Mapper
public interface MyApprovalMapper { 

	List<MyApprovalDTO> selectByIdAll(); //전체 문서정보
	List<MyApprovalDTO> selectIdProceed(String id, String status); //문서 상태에 따라 내가 포함된 문서 리스트 가져오기
	MyApprovalDTO selectByApprover(int dSeq,int id);//문서번호, 기안자로 문서정보 찾기
    List<MyApprovalDTO> selectPaged(int start, int end, int id);//내가 포함된 문서페이지네이션
    MyApprovalDTO2 selectApprovers(int dSeq, int loginId);//문서번호,기안자로 기안정보 찾기
    int getTotalPosts(int id); //전체 포스트 갯수 세기
    MyApprovalDTO3 selectByDSeq(int dSeq); //문서번호로만 문서정보 가져오기
    List<MyApprovalDTO2> selectApproverByDSeq(int dSeq);//문서번호로 기안정보 리스트로 가져오기
    MyApprovalDTO2 selectOrder(int dSeq, int aOder);//문서번호, 기안순서로 기안정보 가져오기
    void updateApproversState(int dSeq, int id, String state);// 현재 결재자가 결재,반려를 선택 한것에 따라 기안정보 db업데이트
    void updateDocStatus(int dSeq, String dStatus);//현재 결재자가 결재,반려를 선택 한것에 따라 문서정보 db업데이트
}
