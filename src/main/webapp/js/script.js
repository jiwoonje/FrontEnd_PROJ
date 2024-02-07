var re = /^[a-zA-Z0-9]{4,12}$/; // 아이디와 패스워드가 적합한지 검사할 정규식
var re2 = /^[0-9a-zA-Z]{1,}@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; // 이메일 조건 정규 표현식
function allCheck(){
  return (idCheck()&&pwCheck());
}

// 아이디
function idCheck(){
  var id = document.getElementById("_id");
  // ^ 이 규칙으로 문장을 시작한다. 
  // [문자 규칙을 입력]
  // {} = [] 안에 포함되는 문자가 최소 4개부터 12개까지
  
  if(id.value == ""){
    window.alert("ID를 입력하세요");
    id.focus();
    id.value = "";
    return false;
  }
  else if(!re.test(id.value)){
    window.alert("아이디는 4~12자와 영어 숫자로만 입력");
    id.focus();
    id.value = "";
    return false;
  }
  else return true;
}


 

function submit(){
	alert("alert 테스트");
}


