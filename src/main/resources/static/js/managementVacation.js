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
		
		switch(data.vacationDTO.vleaveType){
			case "연차":
				document.getElementById("category1").checked = true;
				break;
			case "월차":
				document.getElementById("category2").checked = true;
				break;
			case "보건":
				document.getElementById("category3").checked = true;
				break;
			case "특별":
				document.getElementById("category4").checked = true;
				break;
			case "공가":
				document.getElementById("category5").checked = true;
				break;
		}
		
		
		
		var aprvPa1 = document.getElementById("aprvPa1");
		
		aprvPa1.selectedIndex = 1;
		
		var optionValue = data.userDTO.pname;
		
		alert(optionValue);
		for (var i = 0; i < aprvPa1.options.length; i++) {
		    if (aprvPa1.options[i].value == optionValue) {
		        aprvPa1.selectedIndex = i;
		        break;
		    }
		}

		
		
		/*
		
	{
	  "documentListDTO": {
	    "ddrafterId": 1,
	    "aapproverId": 0,
	    "dcategory": "휴가신청서",
	    "dstatus": "진행중",
	    "dseq": 1,
	    "ddraftingDate": "2023-02-03",
	    "aorderNum": 0,
	    "aapproverState": null
	  },
	  "vacationDTO": {
	    "vseq": 0,
	    "vleaveType": "연차",
	    "vstartDate": "2023-02-05",
	    "vreason": "휴가 신청합니다.",
	    "vendDate": "2023-02-06",
	    "vemployeeContact": "010-2222-2223"
	  },
	  "partnameDTO": [
	    {
	      "unameList": [
	        "김인턴",
	        "김팀장",
	        "김사장"
	      ],
	      "pname": "개발팀",
	      "pid": 1
	    },
	    {
	      "unameList": [
	        "김사원"
	      ],
	      "pname": "영업팀",
	      "pid": 2
	    },
	    {
	      "unameList": [
	        "김주임"
	      ],
	      "pname": "인사팀",
	      "pid": 3
	    }
	  ],
	  "approverList": [
	    {
	      "aapproverId": 4,
	      "aorderNum": 1,
	      "pname": "개발팀",
	      "uname": "김팀장"
	    },
	    {
	      "aapproverId": 5,
	      "aorderNum": 2,
	      "pname": "개발팀",
	      "uname": "김사장"
	    }
	  ],
	  "userDTO": {
	    "uname": "김인턴",
	    "pname": "개발팀",
	    "uposition": "인턴"
	  }
	}
		*/
		
	})
});