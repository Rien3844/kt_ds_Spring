<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<jsp:include page="/WEB-INF/views/templates/header.jsp">
	<jsp:param value="제작진 목록" name="title" />
</jsp:include>

<div class="grid list"></div>
<h1>제작진 목록</h1>
<div>총 ${searchCount}개의 게시글이 검색되었습니다.</div>
<ul class="grid articles">
	<li class="header">
		<ul class="header-item">
			<li>제작진 ID</li>
			<li>제작진 이름</li>
		</ul>
	</li>
</ul>
<c:choose>
	<c:when test="${not empty searchResult}">
		<%-- searchResult가 존재하면, 반복하여 데이터를 보여주고 --%>
		<li class="body"><c:forEach items="${searchResult}"
				var="producer">
				<ul class="body-item">
					<li class="center">${producer.producerId}</li>
					<li class="center">${producer.producerName}</li>
				</ul>
			</c:forEach></li>
	</c:when>
	<c:otherwise>
		<%-- searchResult가 존재하지 않으면, "검색된 데이터가 없습니다"를 보여주고 --%>
		<li class="footer">
			<ul class="footer-item">
				<li class="center">검색된 데이터가 없습니다.</li>
			</ul>
		</li>
	</c:otherwise>
</c:choose>

<div class="btn-group">
	<div class="right-align">
		<a href="/producer/write">새 제작진 추가</a>
	</div>
</div>


<jsp:include page="/WEB-INF/views/templates/footer.jsp" />