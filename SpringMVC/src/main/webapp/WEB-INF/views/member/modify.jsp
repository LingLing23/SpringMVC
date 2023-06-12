<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>modify.jsp</h1>
<h2>http://localhost:8088/member/modify</h2>

	<form action="" method="post">
		
		아이디 : <input type="text" name="userid" value="${resultVO.userid }" readonly> <br>
		비밀번호 : <input type="password" name="userpw" placeholder="비밀번호를 입력하세요"> <br>
		이름 : <input type="text" name="username" value="${resultVO.username }"> <br>
		이메일 : <input type="text" name="useremail" value="${resultVO.useremail }"> <br>
		<input type="submit" value="회원정보수정">
		
	</form>


</body>
</html>