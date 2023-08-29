/**
 * 
 */

 // 현재 날짜를 가져와서 업데이트
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, '0');
  const day = String(today.getDate()).padStart(2, '0');
  document.getElementById("date").textContent = `${year}년${month}월${day}일`;
