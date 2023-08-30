package com.chonamzone.erpproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocumentListDTO {
	
	private  int dSeq;								// 기안서 식별자 
	
	private String dDraftingDate;				// 기안 일자
	
	private String dStatus;						// 진행 상태
	
	private int dDrafterId;						// 기안자
	
	private String dCatetory;					// 문서 종류 (휴가신청서/출장보고서)

}
