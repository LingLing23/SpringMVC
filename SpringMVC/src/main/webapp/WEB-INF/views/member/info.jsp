<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>info.jsp</h1>
<h2>http://localhost:8088/member/info</h2>

<h3>Model 객체에 전달된 정보 꺼내서 사용</h3>
${resultVO }

<h3>Model 객체에 이름이 없는 경우</h3>
<!-- 컨트롤러에서 model.addAttribute(resultVO);로 정보 전달했을 때 -->
<!-- 전달되는 데이터타입의 클래스명을 첫글자만 소문자로 바꿔서 이름으로 사용함 -->
<!--  => resultVO는 MemberVO 클래스의 데이터이므로 memberVO로하면 출력 가능-->
<%-- ${memberVO } --%>


<hr>
<h3>아이디 : ${resultVO.userid }</h3>
<h3>비밀번호 : ${resultVO.userpw }</h3>
<h3>이름 : ${resultVO.username }</h3>
<h3>이메일 : ${resultVO.useremail }</h3>
<h3>회원가입일 : ${resultVO.regdate }</h3>


<h2><a href="/member/main">메인페이지로 이동</a></h2>


</body>
</html>