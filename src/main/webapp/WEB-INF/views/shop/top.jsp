<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div id="nav">
			<a href="shop_list.do?category=com001">컴퓨터</a> | 
			<a href="shop_list.do?category=ele002">가전 제품</a> | 
			<a href="shop_list.do?category=sp003">스포츠</a> | 
			<a href="shop_admin_list.do">상품등록</a>
		</div>
		<hr noshade />
	</div>
</body>
</html>