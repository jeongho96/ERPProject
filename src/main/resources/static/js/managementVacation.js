function createPartname(aprvPa, aprvName1, partnameDTO){
	let partnameContents = "";
	let userContents = "";
	
	for(let i = 0; i < partnameDTO.length; i++){
		partnameContents += "<option>" + partnameDTO[i].pname + "</option>";
	}
	
	aprvPa.innerHTML= partnameContents;
}


function signName(){
	let applicationName = document.getElementById("applicantName").value;
	let name = document.getElementById("name").value;
	
	if(name!=applicationName){
		alert("성명이 올바르지 않습니다.");
		return false;
	}
	
	return true;
}