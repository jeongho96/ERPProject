package com.chonamzone.erpproject.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnameDTO {
	
	private int pId;
	
	private String pName;
	
	private List<UserDTO> userList;
	
	
	@Getter
	@NoArgsConstructor
	public static class MGResponse {
		private int pId;
		private String pName;
		private List<String> uNameList;
	}
}
