<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 등록</title>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
    <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
    <script type="text/javascript" src="/js/movie.js"></script>
  </head>
  <body>
    <h1>영화 등록</h1>
    <form:form modelAttribute="writeVO" method="post" action="/write" enctype="multipart/form-data">
      <div class="grid write">
        <label for="attachPoster">포스터 이미지</label>
        <div id="attach-image" class="attach-image">
          <input id="attachPoster" type="file" name="attachPoster" accept="image/*" />
        </div>

        <label for="title">영화 타이틀</label>
        <div class="input-div">
          <input id="title" type="text" name="title" placeholder="영화 제목을 입력하세요." value="${inputData.title}" />
          <form:errors path="title" cssClass="validation-error" element="div" />
        </div>

        <label for="movieRating">관람 등급</label>
        <input id="movieRating" type="text" name="movieRating" placeholder="관람 등급을 입력하세요." />

        <label for="openDate">오픈 날짜</label>
        <input id="openDate" type="date" name="openDate" />

        <label for="openCountry">개봉 국가</label>
        <input id="openCountry" type="text" name="openCountry" placeholder="개봉 국가를 입력하세요." />

        <label for="runningTime">러닝타임</label>
        <input id="runningTime" type="number" name="runningTime" placeholder="러닝타입을 입력하세요." />

        <label for="introduce">영화 설명</label>
        <textarea name="introduce" id="introduce" placeholder="내용을 입력하세요."></textarea>

        <label for="synopsis">시놉시스</label>
        <div class="input-div">
          <input
            id="synopsis"
            type="text"
            name="synopsis"
            placeholder="줄거리를 입력하세요." 
            value="${inputData.synopsis}"
          />
          <form:errors path="synopsis" cssClass="validation-error" element="div" />
        </div>

        <label for="originalTitle">오리지널타이틀</label>
        <input id="originalTitle" type="text" name="originalTitle" placeholder="오리지널타이틀을 입력하세요." />

        <label for="movieState">개봉상태</label>
        <div class="input-div">
          <input
            id="movieState"
            type="text"
            name="movieState"
            placeholder="개봉상태를 입력하세요.(max 5)"
            value="${inputData.movieState}"
          />
          <form:errors path="movieState" cssClass="validation-error" element="div" />
        </div>

        <label for="language">언어</label>
        <div class="input-div">
          <input
            id="language"
            type="text"
            name="language"
            placeholder="언어을 입력하세요.(max 6)"
            value="${inputData.language}"
          />
          <form:errors path="language" cssClass="validation-error" element="div" />
        </div>

        <label for="budget">쓴돈</label>
        <input id="budget" type="number" name="budget" placeholder="쓴돈을 입력하세요." />

        <label for="profit">번돈</label>
        <input id="profit" type="number" name="profit" placeholder="번돈을 입력하세요." />

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form:form>
  </body>
</html>
