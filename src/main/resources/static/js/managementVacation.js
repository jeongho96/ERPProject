document.addEventListener("DOMContentLoaded", function(){
	
	var dSeq = location.pathname;
	dSeq = dSeq.replace("/management/vacations/", "");

	$.ajax({
		url: "/api/management/vacations/" + dSeq,
		type: "GET"
	}).done(function(data){
		document.getElementById("dateInput").value = data.documentListDTO.ddraftingDate;
		document.getElementById("startDate").value = data.vacationDTO.vstartDate;
		document.getElementById("endDate").value = data.vacationDTO.vendDate;
		document.getElementById("partname").value = data.userDTO.pname;
		document.getElementById("name").value = data.userDTO.uname;
		document.getElementById("position").value = data.userDTO.uposition;
		document.getElementById("reason").value = data.vacationDTO.vreason;
		document.getElementById("emergencyContact").value = data.vacationDTO.vemployeeContact;
		document.getElementById(data.vacationDTO.vleaveType).checked = true;
		
		/*
		{
			"documentListDTO": {
				"ddrafterId": 1,
				"aapproverId": 0,
				"dstatus": "진행중",
				"dcategory": "휴가신청서",
				"dseq": 1,
				"ddraftingDate": "2023-02-03",
				"aorderNum": 0,
				"aapproverState": null
			},
			"vacationDTO": {
				"vseq": 0,
				"vleaveType": "연차",
				"vreason": "휴가 신청합니다.",
				"vstartDate": "2023-02-05",
				"vendDate": "2023-02-06",
				"vemployeeContact": "010-2222-2223"
			},
			"approverList": [
				{
					"aapproverId": 4,
					"pname": "개발팀",
					"uname": "김팀장"
				},
				{
					"aapproverId": 5,
					"pname": "개발팀",
					"uname": "김사장"
				}
			],
			"userDTO": {
				"pname": "개발팀",
				"uname": "김인턴",
				"uposition": "인턴"
			}
		}
		*/
		
	})
});