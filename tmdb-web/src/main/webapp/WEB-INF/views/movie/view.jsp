<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>영화 목록</title>
</head>
<body>
	<h1>영화 조회</h1>
	<div>영화 아이디: ${article.movieId}</div>
	<div>포스트 URL: ${article.posterUrl}</div>
	<div>영화 타이틀: ${article.title}</div>
	<div>관람 등급: ${article.movieRating}</div>
	<div>오픈 날짜: ${article.openDate}</div>
	<div>개봉 국가: ${article.openCountry}</div>
	<div>러닝타임: ${article.runningTime}</div>
	<div>영화 설명: ${article.introduce}</div>
	<div>시놉시스: ${article.synopsis}</div>
	<div>오리지널타이틀: ${article.originalTitle}</div>
	<div>개봉상태: ${article.movieState}</div>
	<div>언어: ${article.language}</div>
	<div>쓴돈: ${article.budget}</div>
	<div>번돈: ${article.profit}</div>
</body>
</html>
