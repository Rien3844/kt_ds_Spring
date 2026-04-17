<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>멤버 목록</title>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
  </head>
  <body></body>
  <div class="grid list"></div>
  <h1>멤버 목록</h1>
  <div>총 ${searchCount}명의 멤버가 가입했습니다.</div>
  <table class="grid">
    <colgroup>
    <col width="200"/>
    <col width="100"/>
    <col width="100"/>
    </colgroup>
    <thead>
        <tr>
            <th>이메일</th>
            <th>닉네임</th>
            <th>비밀번호</th>
        </tr>
    </thead>
    <tbody>
      <c:choose>
        <c:when test="${not empty searchResult}">
          <c:forEach items="${searchResult}" var="member">
            <tr>
              <td>
                <a href="view/${member.email}">${member.email}</a>
              </td>
              <td>${member.name}</td>
              <td>${member.password}</td>
            </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="3">등록된 회원이 없습니다.</td>
          </tr>
        </c:otherwise>
      </c:choose>
    </tbody>
  </table>
  <div class="btn-group">
    <div class="right-align">
      <a href="/regist">새로운 회원 등록</a>
    </div>
  </div>
</html>
