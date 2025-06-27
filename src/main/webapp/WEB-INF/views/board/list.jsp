<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
	<div class="container p-0">
        <main>
            <div class="clearfix py-0 row align-items-center">
            	<div class="col-2">
            		<select class="form-select form-select-sm ">
	            		<option>10개씩보기</option>
	            		<option>20개씩보기</option>
	            		<option>50개씩보기</option>
	            		<option>100개씩보기</option>
	            	</select>
            	</div>
            	<form class="col input-group search-form">
            		<select class="form-select form-select-sm" name="type">
	            		<option value="T">제목</option>
	            		<option value="C">내용</option>
	            		<option value="I">작성자</option>
	            		<option value="TC">제목+내용</option>
	            		<option value="TI">제목+작성자</option>
	            		<option value="CI">내용+작성자</option>
	            		<option value="TCI">제목+내용+작성자</option>
	            	</select>
				    <input type="text" class="form-control form-control-sm w-75" placeholder="Search" name="keyword">
				    <input type="hidden" name="page" value="1">
				    <input type="hidden" name="amount" value="${pageDto.cri.amount}">
				    <input type="hidden" name="cno" value="${pageDto.cri.cno}">
				    <button class="btn btn-success btn-sm" type="submit">Go</button>
            	</form>
            	<script>
            	$(".search-form").submit(function() {
            		event.preventDefault();
            		this.keyword.value = encodeURIComponent(this.keyword.value)
            		this.submit();
            	});
            	</script>
            	<div class="col-2">
                	<a href="write?${pageDto.cri.qs2}" class="btn btn-primary btn-sm float-end"><i class="fa-solid fa-pen-fancy"></i> 글쓰기</a>
                </div>
            </div>
            
            <c:forEach items="${cate}" var="c">
	    		<c:if test="${c.cno == pageDto.cri.cno}">
             	<c:choose>
            		<c:when test="${c.getCViewType() == 'GALLERY'}">
    				<jsp:include page="list_template/gallery.jsp" />
            		</c:when>
            		<c:when test="${c.getCViewType() == 'LIST'}">
    				<jsp:include page="list_template/list.jsp" />
            		</c:when>
            		<c:when test="${c.getCViewType() == 'FEED'}">
    				
            		</c:when>
    			</c:choose> 
	    		</c:if>
    		</c:forEach>
            
            
            <ul class="pagination justify-content-center">
              <c:if test="${pageDto.doubleLeft}">
			  	<li class="page-item"><a class="page-link" href="list?page=1&${pageDto.cri.qs}"><i class="fa-solid fa-angles-left"></i></a></li>
			  </c:if>			  
			  <c:if test="${pageDto.left}">
			  	<li class="page-item"><a class="page-link" href="list?page=${pageDto.start - 1}&${pageDto.cri.qs}"><i class="fa-solid fa-angle-left"></i></a></li>
			  </c:if>
			  
			  <c:forEach begin="${pageDto.start}" end="${pageDto.end}" var="i">
			  	 <li class="page-item ${pageDto.cri.page == i ? 'active' : ''}"><a class="page-link" href="list?page=${i}&${pageDto.cri.qs}">${i}</a></li>
			  </c:forEach>
			  
			  <c:if test="${pageDto.right}">
			  <li class="page-item"><a class="page-link" href="list?page=${pageDto.end + 1}&${pageDto.cri.qs}"><i class="fa-solid fa-angle-right"></i></a></li>
			  </c:if>
			  
			  <c:if test="${pageDto.doubleRight}">
			  	<li class="page-item"><a class="page-link" href="list?page=${pageDto.realEnd}&${pageDto.cri.qs}"><i class="fa-solid fa-angles-right"></i></a></li>
			  </c:if>
			</ul>
        </main>
    </div>
<%@ include file="../common/footer.jsp" %>

</body>
</html>