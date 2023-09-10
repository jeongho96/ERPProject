package com.chonamzone.erpproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.DocumentListDTO;

@Mapper
public interface DocumentListMapper {
	// 전체 문서 목록 출력
	List<DocumentListDTO.MapperData> getManagementAllList(Map<String, Integer> pagination);
	// 결재상태(status)에 따른 문서 목록 출력
	List<DocumentListDTO.MapperData> getManagementList(Map<String, Object> map);
	// 문서번호(dSeq)를 이용해 문서 목록 출력
	DocumentListDTO.MapperData getDocumentListByDSeq(int dSeq);
	// 문서번호(dSeq)를 이용해 기안일 수정
	void updateDDraftingDate(Map<String, Object> map);
	// 문서 목록 데이터 저장
	void insert(DocumentListDTO documentList);
}
