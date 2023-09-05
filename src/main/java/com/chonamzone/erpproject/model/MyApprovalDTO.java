package com.chonamzone.erpproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyApprovalDTO {
	private String dCategory; 			
	private int dSeq;		
	private int dDrafterId;		
	private int aApproverId;	
	private String dDraftingDate;			
	private String dStatus;
}