<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<div class="list-group">
                <div class="list-group-item small">
                    <div class="row text-center align-items-center small text-muted">
                        <div class="col-1 small">글번호</div>
                        <div class="col-1 small">카테고리</div>
                        <div class="col text-start">제목</div>
                        <div class="col-1 small">작성일</div>
                        <div class="col-1 small">조회수</div>
                    </div>
                </div>
                <a href="#" class="list-group-item list-group-item-action list-group-item-secondary">
                    <div class="row text-center align-items-center small text-muted">
                        <div class="col-1 small">1</div>
                        <div class="col-1 small">자유</div>
                        <div class="col text-start fw-bold text-black">제목</div>
                        <div class="col-1 small"><span class="small">25.06.13</span></div>
                        <div class="col-1 small"><span class="small">13</span></div>
                    </div>
                </a>
                <c:forEach items="${boards}" var="board">
                <a href="view?bno=${board.bno}&${pageDto.cri.qs2}" class="list-group-item list-group-item-action">
                    <div class="row text-center align-items-center small text-muted">
                        <div class="col-1 small">${board.bno}</div>
                        <div class="col-1 small">${board.cno}</div>
                        <div class="col text-start text-black">
                        	<c:if test="${board.bno != board.grp}">
                        	<i class="fa-solid fa-reply text-secondary" style="transform:rotate(180deg); margin-left: ${(board.depth-2)*14}px"></i>
                        	</c:if>
                        	${board.title} 
                        	<span class="small text-danger fw-bold">${board.replyCnt}</span>
                        	<c:if test="${board.attachCnt > 0}">
                        	<i class="fa-solid fa-paperclip text-muted"></i>
                        	</c:if>
                        </div>
                        <div class="col-1 small">
                        	<span class="small">
	                        <fmt:parseDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate"/>
	                        <fmt:formatDate value="${parsedDate}" pattern="yy.MM.dd"/>
                        	</span>
                        </div>
                        <div class="col-1 small"><span class="small">${board.cnt}</span></div>
                    </div>
                </a>
                </c:forEach>
            </div>
            