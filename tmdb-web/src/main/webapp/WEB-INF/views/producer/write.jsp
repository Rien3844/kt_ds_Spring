<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/views/templates/header.jsp">
	<jsp:param value="제작진 등록" name="title" />
</jsp:include>

<h1>제작진 추가</h1>
<form:form>
	<label for="name">제작진 이름</label>
	<div class="input-div">
		<input type="text" id="name" name="name"
			placeholder="이름을 입력하세요." value="${inputData.name}" />
		<form:errors path="name" cssClass="validation-error"
			element="div" />
	</div>
	
	<div class="btn-group">
		<div class="right-align">
			<input type="submit" value="저장" />
		</div>
	</div>
	</div>
</form:form>

<jsp:include page="/WEB-INF/views/templates/footer.jsp" />