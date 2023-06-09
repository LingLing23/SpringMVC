<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>main.jsp</h1>
<h2>http://localhost:8088/member/main</h2>


<!-- 로그인 정보가 있을때만 페이지 사용 -->
<c:if test="${empty id }">
	<c:redirect url="/member/login" />
</c:if>


<!-- 로그인 아이디 정보 확인 -->
아이디 : ${sessionScope.id } <br>

<input type="button" value="로그아웃" onclick="location.href='/member/logout';">

<hr>

<h2><a href="/member/info">회원정보 조회</a></h2>
<h2><a href="/member/modify">회원정보 수정</a></h2>
<h2><a href="/member/delete">회원정보 삭제</a></h2>


<c:if test="${!empty sessionScope.id && id.equals('admin')}">
	<h2><a href="/member/list">회원정보 목록</a></h2>
</c:if>









</body>
</html>