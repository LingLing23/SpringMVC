<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>login.jsp</h1>
<h2>http://localhost:8088/member/login</h2>

<fieldset>
	<form action="" method="post">	<!-- action 속성을 생략하기도 함 (표준은 적긴 적어야 함) -->
		아이디 : <input type="text" name="userid"> <br>
		비밀번호 : <input type="password" name="userpw"> <br>	
		
		<input type="button" value="회원가입" onclick="location.href='/member/join';">
		<input type="submit" value="로그인">	
	</form>
</fieldset>


</body>
</html>