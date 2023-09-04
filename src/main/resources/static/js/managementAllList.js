document.addEventListener("DOMContentLoaded", function(){
	
	$.ajax({
		url: "/api/management/all?page=1",
		type: "GET"
	}).done(function(data){
		var contents = "<tr>";
		contents += "<th>기안일자</th>";
		contents += "<th>구분</th>";
		contents += "<th>기안자</th>";
		contents += "<th>결재자</th>";
		contents += "<th>결재순서</th>";
		contents += "<th>진행상태</th>";
		contents += "<th>보기</th>";
		contents += "</tr>";
		
		for(var i=0; i<data.length; i++){
			
			
			contents += "<tr>";
			contents += "<td>" + data[i].ddraftingDate + "</td>";
			contents += "<td>" + data[i].dcategory +"</td>";
			contents += "<td>" + data[i].ddrafterName + "</td>";
			contents += "<td>" + data[i].aapproverName + "</td>";
			contents += "<td>" + data[i].aorderNum + "</td>";
			contents += "<td>" + data[i].aapproverState + "</td>";
			
			if (data[i].dcategory == "휴가신청서"){
				var clickURL = "vacations";
			} else {
				var clickURL = "travels";
			}
			contents += "<td><a href='/management/" + clickURL + "/" + data[i].dseq + "'>보기</a></td>";
			contents +="</tr>";
		}
		
		
		$("#contents").html(contents);
		
	})
});


