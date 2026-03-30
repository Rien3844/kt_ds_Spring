<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
    <title>회원 가입</title>
  </head>
  <body>
    <h1>회원 가입</h1>
    <form method="post" action="/regist">
      <div class="grid regist">

        <label for="email">이메일</label>
        <input
          id="email"
          type="email"
          name="email"
          placeholder="이메일을 입력하세요.
        "
        />
        
        <label for="name">이름</label>
        <input
          id="name"
          type="text"
          name="name"
          placeholder="이름을 입력하세요."
        />

        <label for="password">비밀번호</label>
        <input
          name="password"
          id="password"
          type="password"
        ></input>

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
