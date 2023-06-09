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


<h1>list.jsp</h1>
<h2>http://localhost:8088/memeber/list</h2>

<h2>회원목록 (memberList)</h2>


<c:forEach var="vo" items="${memberList }">
	이름 : ${vo.username }, 이메일 : ${vo.useremail } <hr>
</c:forEach>


<hr>
${memberList }


</body>
</html>