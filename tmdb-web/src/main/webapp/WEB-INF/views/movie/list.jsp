<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>영화 목록</title>
</head>
<body>
	<h1>영화 목록</h1>
	<div>총 ${searchCount}개의 영화가 검색되었습니다.</div>
	<table class="grid">
		<colgroup>
			<col width="180" />
			<col width="100" />
			<col width="*" />
			<col width="70" />
			<col width="180" />
			<col width="60" />
			<col width="60" />
			<col width="300" />
			<col width="300" />
			<col width="150" />
			<col width="80" />
			<col width="50" />
			<col width="30" />
			<col width="30" />
		</colgroup>
		<thead>
			<tr>
				<th>영화 아이디</th>
				<th>포스트 URL</th>
				<th>영화 타이틀</th>
				<th>관람 등급</th>
				<th>오픈 날짜</th>
				<th>개봉 국가</th>
				<th>러닝타임</th>
				<th>영화 설명</th>
				<th>시놉시스</th>
				<th>오리지널타이틀</th>
				<th>개봉상태</th>
				<th>언어</th>
				<th>쓴돈</th>
				<th>번돈</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty searchResult}">
					<!-- searchResult가 not empty면 안의 내용을 실행해라. -->
					<!-- searchResult가 존재하면, 반복하여 데이터를 보여준다. -->
					<c:forEach items="${searchResult}" var="movie">
						<tr>
							<td><a href="/view/${movie.movieId}">${movie.movieId}</a></td>
							<td>${movie.posterUrl}</td>
							<td>${movie.title}</td>
							<td>${movie.movieRating}</td>
							<td>${movie.openDate}</td>
							<td>${movie.openCountry}</td>
							<td>${movie.runningTime}</td>
							<td>${movie.introduce}</td>
							<td>
								<details>
										<summary>내용 보기</summary>
									<div class="preview">
										<p>${movie.synopsis}</p>
									</div>
								</details>
							</td>
							<td>${movie.originalTitle}</td>
							<td>${movie.movieState}</td>
							<td>${movie.language}</td>
							<td>${movie.budget}</td>
							<td>${movie.profit}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<!-- searchResult가 존재하지 않으면, "검색된 데이터가 없습니다"를 보여주고, -->
					<tr>
						<td colspan="14">검색된 데이터가 없습니다</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>
