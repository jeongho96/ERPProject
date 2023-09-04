package com.chonamzone.erpproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyApprovalDTO {
	private String dCategory; 			
	private String dSeq;		
	private String dDrafterId;		
	private String aApproverId;	
	private String dDraftingDate;			
	private String dStatus;
}