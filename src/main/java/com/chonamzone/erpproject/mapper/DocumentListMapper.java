package com.chonamzone.erpproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.DocumentListDTO;

@Mapper
public interface DocumentListMapper {
	List<DocumentListDTO.MapperData> getManagementAllList(Map<String, Integer> pagination);
	List<DocumentListDTO.MapperData> getManagementList(Map<String, Object> map);
	DocumentListDTO.MapperData getDocumentListByDSeq(int dSeq);
	void updateDDraftingDate(Map<String, Object> map);
	void insert(DocumentListDTO documentList);
}
