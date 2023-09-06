package com.chonamzone.erpproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chonamzone.erpproject.model.DocumentListDTO;

@Mapper
public interface DocumentListMapper {
	List<DocumentListDTO.MapperData> getManagementList(Map<String, Integer> pagination);
	DocumentListDTO.MapperData getDocumentListByDSeq(int dSeq);
}
