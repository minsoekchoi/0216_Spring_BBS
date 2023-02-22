<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a:link {
	text-decoration: none;
	color: navy
}

a:visited {
	text-decoration: none;
	color: navy
}

a:hover {
	text-decoration: none;
	color: red
}

body {
	text-align: center
}

hr {
	width: 600px;
	border: 1px;
	color: navy;
}

div#header, div#nav {
	width: 600px;
	margin: 10px auto;
	text-align: center;
	font-size: 15px;
}

div#wrap {
	margin: 0 auto;
}
.title {
	font-size: 25px;
}
</style>
</head>
<body>
	<div id="wrap">
		<hr noshade />
		<div id="header">
			<span class="title">
				ICTEDU SHOPPING CENTER
			</span>
		</div>
		<hr noshade />
			<c:choose>
 			<c:when test="${m_id == 'admin'}">
		<div id="nav">	
			<a href="shop_list.do?category=com001">컴퓨터</a> | 
			<a href="shop_list.do?category=ele002">가전 제품</a> | 
			<a href="shop_list.do?category=sp003">스포츠</a> | 
			<a href="shop_admin_list.do">상품등록</a> | 
			<a href="login.do">로그아웃</a>
		</div>
 			</c:when>
 			<c:otherwise>
		<div id="nav">	
			<a href="shop_list.do?category=com001&m_id=${m_id}">컴퓨터</a> | 
			<a href="shop_list.do?category=ele002&m_id=${m_id}">가전 제품</a> | 
			<a href="shop_list.do?category=sp003&m_id=${m_id}">스포츠</a> | 
			<a href="shop_showcart.do?m_id=${m_id}">내장바구니</a> | 
			로그인이 되었습니다.<a href="logout_mystore_save.do?m_id=${m_id}">[로그아웃]</a>
		</div>	
			</c:otherwise>
			
 			
		</c:choose>
		<hr noshade />
	</div>
</body>
</html>