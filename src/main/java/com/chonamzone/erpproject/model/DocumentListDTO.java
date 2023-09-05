package com.chonamzone.erpproject.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DocumentListDTO {
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MapperData {
		private int dSeq;										// 결재라인 식별자
		private LocalDate dDraftingDate;				// 기안 일자
		private String dStatus;								// 진행 상태
		private int dDrafterId;								// 기안자
		private String dCategory;							// 문서 종류 (휴가신청서/출장보고서)
		private int aApproverId;							// 결재자
		private int aOrderNum;								// 결재 순서
		private String aApproverState;					// 결재 여부
	}
	
	
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MGResponse {
		private int dSeq;
		private LocalDate dDraftingDate;				// 기안 일자
		private String dStatus;								// 진행 상태
		private String dDrafterName;						// 기안자 이름
		private String dCategory;							// 문서 종류 (휴가신청서/출장보고서)
		private String aApproverName;					// 결재자 이름
		private int aOrderNum;								// 결재 순서
		private String aApproverState;					// 결재 여부
		
	
		public MGResponse(MapperData mapperData) {
			this.dSeq = mapperData.getDSeq();
			this.dDraftingDate = mapperData.getDDraftingDate();
			this.dStatus = mapperData.getDStatus();
			this.dCategory = mapperData.getDCategory();
			this.aOrderNum = mapperData.getAOrderNum();
			this.aApproverState = mapperData.getAApproverState();
		}
		
		public void idToName(String drafterName, String approverName) {
			this.dDrafterName = drafterName;
			this.aApproverName = approverName;
		}
	
	}
	
	
}
