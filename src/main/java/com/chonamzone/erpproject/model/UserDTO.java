package com.chonamzone.erpproject.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
	
	private int uId;														// 유저식별자 	
	
	private String uPwd;												// 비밀번호
	
	private String uPosition;										// 직위
	
	private String uName;											// 이름
	
	private String uPhone;											// 연락처
	
	private String uEmail;											// 사내 이메일
	
	private String uHireDate;										// 입사일
	
	private int pId;														// 부서 식별자
		
	private List<DocumentListDTO> documemtList;		// 문서 목록 리스트
	
	
	@Getter
	@NoArgsConstructor
	public static class MGResponse {
		private String uName;
		private String uPosition;
		private String pName;
	}
	
}
