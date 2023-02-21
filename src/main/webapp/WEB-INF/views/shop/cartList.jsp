<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 10px auto;
	width: 1000px;
	border-collapse: collapse;
	font-size: 15pt;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
	padding: 10px;
}
</style>
<script type="text/javascript">
	function edit_go(f) {
		f.action="shop_editcart.do";
		f.submit();
	}
	function delete_go(f) {
		f.action="shop_deletecart.do";
		f.submit();
	}

</script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<h2>장바구니 내용</h2>
	<table>
		<thead>
			<tr bgcolor="#dedede">
				<th>제품번호</th>
				<th width="25%">제품명</th>
				<th>단가</th>
				<th>수량</th>
				<th>금액</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty clist}">
					<tr>
						<td colspan="6"><h3>장바구니가 비었습니다.</h3></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${clist}">
						<tr>
							<td>${k.p_num}</td>
							<td>${k.p_name}</td>
							<td>시중가 : <fmt:formatNumber value="${k.p_price}" pattern="#,##0"/>
								<font color="red">(할인가 : <fmt:formatNumber value="${k.p_saleprice}" pattern="#,##0"/>)</font>
							 </td>
							<td>
								<form method="post">
									<input type="number" min="1" name="su" value="${k.qaunt}" style="width: 30px;">
									<input type="hidden" name="idx" value="${k.idx}">
									<input type="button" value="수정" onclick="edit_go(this.form)">
								</form>
							</td>
							<td><fmt:formatNumber value="${k.totalPrice}" pattern="#,##0"/></td>
							<td>
								<form method="post">
									<input type="hidden" name="idx" value="${k.idx}">
									<input type="button" value="삭제" onclick="delete_go(this.form)">
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" style="text-align: right; padding: 10px 50px">총 결제 금액 : <fmt:formatNumber value="${total}" pattern="#,##0"/></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>










