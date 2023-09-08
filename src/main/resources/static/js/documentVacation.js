/**
 * 
 */

 // 현재 날짜를 가져와서 업데이트
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, '0');
  const day = String(today.getDate()).padStart(2, '0');
  const formattedDate = `${year}-${month}-${day}`; // 날짜 형식을 yyyy-MM-dd로 맞춤
  document.getElementById("dDraftingDate").value = formattedDate;
  /*document.getElementById("dateInput").textContent = `${year}년${month}월${day}일`;*/
  /*document.getElementById("dateInput").value = ${year}-${month}-${day};*/
