<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>doC.jsp</h1>

http://localhost:8088/doC?msg=hello
<hr>



JSP 표현식(파라미터)  :  <%=request.getParameter("msg") %> <hr>
EL 표현식(파라미터)   :  ${param.msg } <hr>
EL 표현식             :  ${msg } <hr>
EL 표현식             :  ${age } <hr>
<!-- ModelAttribute 어노테이션 사용으로
 주소줄의 데이터가 저장되어 자동으로 뷰페이지에서 el표현식으로 데이터 사용 가능 ! -->
흠
 
 
@${vo }@ <br>
<!-- MemberVO 객체의 경우, @ModelAttribute("파라미터이름")를 생략한 것과 같음. -->
<!-- -> 이름없이 정보를 저장한 것과 같으므로, -->
<!--    이름이 없는 경우, 전달되는 데이터 타입의 첫글자를 소문자로 변경한 이름을 사용. -->
<%--    => 그래서 MemberVO가 아닌,  @${memberVO }@ 해야 출력 가능. --%>
@${memberVO }@ <br>
@${tel }@ <br>




</body>
</html>