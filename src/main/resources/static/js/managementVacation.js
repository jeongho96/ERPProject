function createPartname(aprvPa, aprvName1, partnameDTO){
	let partnameContents = "";
	let userContents = "";
	
	for(let i = 0; i < partnameDTO.length; i++){
		partnameContents += "<option>" + partnameDTO[i].pname + "</option>";
	}
	
	aprvPa.innerHTML= partnameContents;
}