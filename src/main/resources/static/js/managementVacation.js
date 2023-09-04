document.addEventListener("DOMContentLoaded", function(){
	
	var dSeq = location.pathname;
	dSeq = dSeq.replace("/management/vacations/", "");

	$.ajax({
		url: "/api/management/vacations/" + dSeq,
		type: "GET"
	}).done(function(data){
		document.getElementById("startDate").value = data.vstartDate;
	
		
		
		/*{
			"vendDate": "2023-02-06 00:00:00",
			"vreason": "휴가 신청합니다.",
			"vseq": 0,
			"vstartDate": "2023-02-05 00:00:00",
			"vleaveType": "연차",
			"vemployeeContact": "010-2222-2223"
		}*/
	})


});