<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>사용자 목록</title>
  </head>
  <body>
    <h1>회원 가입한 사용자</h1>
    <div>총 ${searchCount}명의 사용자가 가입했습니다.</div>
    <div>이메일: ${searchResult[0].email}</div>
    <div>이름: ${searchResult[0].name}</div>
    <div>비밀번호: ${searchResult[0].password}</div>
  </body>
</html>
