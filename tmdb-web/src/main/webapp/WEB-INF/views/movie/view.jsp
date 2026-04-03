<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>영화 목록</title>
<link rel="stylesheet" type="text/css" href="/css/hello-spring.css"/>
</head>
<body>
	<h1>영화 조회</h1>
	<span>영화 아이디</span>
	<div>${article.movieId}</div>
	
	<span>포스터</span>
	
      <div>
          <img src="/file/${article.poster[0].posterGroupId}/${article.poster[0].posterNum}" alt="${article.poster[0].displayName}" />
      </div>
      
    <span>영화 타이틀</span>
	<div>${article.title}</div>
	
    <span>관람 등급</span>
	<div>${article.movieRating}</div>
    
    <span>오픈 날짜</span>
	<div>${article.openDate}</div>
    
    <span>개봉 국가</span>
	<div>${article.openCountry}</div>
    
    <span>러닝타임</span>
	<div>${article.runningTime}</div>
    
    <span>영화 설명</span>
	<div>${article.introduce}</div>
    
    <span>시놉시스</span>
	<div>${article.synopsis}</div>
    
    <span>오리지널타이틀</span>
	<div>${article.originalTitle}</div>
    
    <span>개봉상태</span>
	<div>${article.movieState}</div>
    
    <span>언어</span>
	<div>${article.language}</div>
    
    <span>쓴돈</span>
	<div>${article.budget}</div>
    
    <span>번돈</span>
	<div>${article.profit}</div>
</body>
</html>
