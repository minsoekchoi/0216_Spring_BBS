<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 10px auto;
	width: 800px;
	border-collapse: collapse;
	font-size: 15px;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
	padding: 10px;
}
</style>
<script type="text/javascript">
	function add_cart() {
		location.href = "shop_addcart.do?idx=${shop_VO.idx}&m_id=${m_id}";
	}
	function show_cart() {
		location.href = "shop_showcart.do?m_id=${m_id}";
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<table>
		<tr>
			<td width="40%">제품Category</td>
			<td width="60%">${shop_VO.category}</td>
		</tr>
		<tr>
			<td width="40%">제품번호</td>
			<td width="60%">${shop_VO.p_num}</td>
		</tr>
		<tr>
			<td width="40%">제품이름</td>
			<td width="60%">${shop_VO.p_name}</td>
		</tr>
		<tr>
			<td width="40%">제품판매사</td>
			<td width="60%">${shop_VO.p_company}</td>
		</tr>
		<tr>
			<td width="40%">제품가격</td>
			<td width="60%">시중가 : <fmt:formatNumber value="${shop_VO.p_price}" pattern="#,##0" />원
			<font color="red">(할인가: <fmt:formatNumber value="${shop_VO.p_saleprice}" pattern="#,##0" />원)
			</font></td>
		</tr>
		<tr>
			<td colspan="2" style="">${shop_VO.p_content}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<img src="resources/images/${shop_VO.p_image_l}">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button onclick="add_cart()"> 장바구니 담기 </button>
				
				<button onclick="show_cart()"> 장바구니 보기 </button>
			</td>
		</tr>
	</table>
</body>
</html>