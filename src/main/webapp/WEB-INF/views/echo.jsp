<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
	<div class="container my-2">
		<textarea id="chatBox" readonly class="form-control resize-none" rows="10"></textarea>
		<form id="inputForm" class="input-group mt-3">
		<input class="form-control" placeholder="메세지 작성"><button class="btn btn-primary">발송</button>
		</form>
	</div>
	<script>
		let socket;

		$(function() {
			socket = new WebSocket(`ws://\${location.host}${cp}/echo`)

			socket.onopen = () => console.log("서버에 접속됨!");
			socket.onmessage = function(event) {
				console.log(event.data);
				$("#chatBox").val(function(i, text) {
					return text += event.data + "\n" 
				})
			}
			socket.onerror = () => console.log("서버 접속 실패");

			$("#inputForm").submit(function() {
				event.preventDefault();
				socket.send($(this).find("input").val())
				$(this).find("input").val("");
			})
		});
	</script>
</body>
</html>