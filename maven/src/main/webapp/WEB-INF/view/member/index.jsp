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
	<c:if test="${sessionScope.memberInfo == null }">
		<input type="button" value="로그인" onclick="location.href='form.do'">
	</c:if>
	<c:if test="${sessionScope.memberInfo != null }">
		<input type="button" value="로그아웃" onclick="location.href='logout.do'">
	</c:if>
	<input type="button" value="마이페이지" onclick="location.href='mypage.do'">
	<input type="button" value="등록" onclick="location.href='write.do'"><br>
	<c:forEach var="member" items="${list }">
		${member.mno }, ${member.mname }, ${member.email }<br>
	</c:forEach>
</body>
</html>