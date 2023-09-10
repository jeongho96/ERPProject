function signName(){
	let applicationName = document.getElementById("applicantName").value;
	let name = document.getElementById("name").value;
	
	if(name!=applicationName){
		alert("성명이 올바르지 않습니다.");
		return false;
	}
	
	return true;
}