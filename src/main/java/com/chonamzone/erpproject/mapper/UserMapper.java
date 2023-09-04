package com.chonamzone.erpproject.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public String getNameById(int id);
}
