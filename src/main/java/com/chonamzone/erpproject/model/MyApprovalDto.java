package com.chonamzone.erpproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MyApprovalDto {
	private String dCategory; 			
	private String dSeq;		
	private String dDrafterId;		
	private String aApproverId;	
	private String dDraftingDate;			
	private String dStatus;
	

}
