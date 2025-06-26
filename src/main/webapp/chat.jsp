<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>웹소켓 echo 테스트</h2>
	<form name="frm">
		<input type="text" name="msgInput" placeholder="보낼 메세지">
		<button>전송</button>
	</form>
	<ul id="chatBox">
	</ul>
	<script>
	let socket;
	
	window.onload = function() {
		socket = new WebSocket("ws://" + location.host + "${cp}/echo");

		socket.onopen = () => console.log('서버와 연결 성공');
		
		socket.onmessage = (event) => {
			const li = document.createElement("li");
			li.textContent = event.data;
			document.querySelector("#chatBox").appendChild(li);
		}

		socket.onclose = () => console.log("서버 연결 종료");
		socket.onerror = err => console.log("서버오류 : " + err);
	}
	document.querySelector("form").addEventListener("submit", event=>{
		event.preventDefault();
		const input = event.target.msgInput;
		const msg = input.value;
		socket.send(msg);
		input.value = "";
	})
	</script>
</body>
</html>