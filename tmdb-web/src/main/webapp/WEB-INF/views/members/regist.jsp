<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원 가입</title>
    <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
    <script type="text/javascript" src="/js/members.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/regist.css" />
  </head>
  <body>
    <!-- action ==> form 내부의 value를 전송할 엔드포인트 -->
    <div class="regist-div">
      <h1>회원 가입</h1>
      <form:form modelAttribute="registVO" method="post" action="/regist" enctype="multipart/form-data">
        <div class="grid regist">
          <label for="email" id="h">이메일</label>
          <div class="input-div">
            <input type="email" id="email" name="email" placeholder="이메일을 입력하세요." value="${inputData.email}" />
            <form:errors path="email" cssClass="validation-error" element="div" />
          </div>

          <label for="name">이름</label>
          <div class="input-div">
            <input type="text" id="name" name="name" placeholder="이름을 입력하세요." value="${inputData.name}" />
            <form:errors path="name" cssClass="validation-error" element="div" />
          </div>

          <label for="password">비밀번호</label>
          <div class="input-div">
            <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요." />
            <form:errors path="password" cssClass="validation-error" element="div" />
          </div>

          <!-- 비밀번호 두번 입력하기 ==> 두 비밀번호가 일치할 때 회원가입가능 -->
          <label for="confirm-password">비밀번호 확인</label>
          <div class="input-div">
            <input
              type="password"
              id="confirm-password"
              name="confirm-password"
              placeholder="비밀번호를 다시 입력해주세요."
            />
          </div>

          <!-- 비밀번호 한번 입력하기 ==> 비밀번호 확인하는기능 -->
          <label for="show-password">비밀번호 확인하기</label>
          <input type="checkbox" id="show-password" />

          <div class="btn-group">
            <input type="submit" value="가입하기" />
          </div>
        </div>
      </form:form>
    </div>
  </body>
</html>
