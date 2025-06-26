<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="common/head.jsp"%>
</head>
<body>
<script>
$(function() {
	/* for(let i = 0 ; i < 100 ; i++) {
		$.post("${cp}/sample/"+i, function(data) {
			console.log(data.result);
		})
	} */
	
	let cnt = 1;
	
	const myFunc = function(data) {
		console.log(data.result);
	} 
	
	// 유사 promise
	
/* 	$.ajax({
		url : '${cp}/sample/',
		method : 'post'
	})
	.done(myFunc)
	.done(myFunc)
	.done(myFunc)
	.done(myFunc)
	.done(myFunc) */
	fetch('${cp}/sample/', {
		method : 'POST'
	}).then(function(res) {
		return res.json();
	}).then(function(data) {
		console.log(data);
		return fetch('${cp}/sample/', {
			method : 'POST'
		});
	}).then(function(res) {
		return res.json();
	}).then(function(data) {
		console.log(data);
		return fetch('${cp}/sample/', {
			method : 'POST'
		});
	}).then(function(res) {
		return res.json();
	}).then(function(data) {
		console.log(data);
	});
	
	// 결제 모듈모듈을 사용하기위한 사전 데이터
	
	// 후원대상, 누구, 액수, 주문 코드
	// 과자3개, 음료수2개
	
	// 주문상태 피드, 결제모듈 발급 코드, 어떤 결제방식,
	
	
	
})
</script>
</body>
</html>