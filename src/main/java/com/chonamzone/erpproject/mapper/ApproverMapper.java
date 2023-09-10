package com.chonamzone.erpproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.ApproverDTO;
import com.chonamzone.erpproject.model.VacationDTO;
import com.chonamzone.erpproject.model.VacationDTO.MGVacationDTO;

@Mapper
public interface ApproverMapper {
	// 문서번호(dSeq)로 결재자 사번, 이름, 부서명 가져오기
	List<ApproverDTO.MGResponse> getApproverDetailsListByDSeq(int dSeq);
	// 결재자 변경(문서 테이블에는 approverId만 존재하기 때문에 id만 변경함)
	void updateApproverId(Map<String, Object> aprvMap);
	// 결재자 정보 insert
	void insert(ApproverDTO approver);
}
