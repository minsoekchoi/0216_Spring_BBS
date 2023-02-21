<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 상품 등록창</title>
<style type="text/css">
#login table {
	width: 600px;
	margin: 0 auto;
	margin-top: 50px;
	border: 1px solid black;
	border-collapse: collapse;
	font-size: 14px;
}

#login table caption {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 10px;
}

#login table th {
	text-align: center;
	border: 1px solid black;
	padding: 4px 10px;
	font-size: 14px;
}

#login table td {
	text-align: left;
	border: 1px solid black;
	padding: 4px 10px;
}
</style>
<script type="text/javascript">
	function product_reg_go(f) {
		if (f.category.value.trim().length <= 0) {
			alert("카테고리를 선택하세요");
			return;
		}
		if (f.p_num.value.trim().length <= 0) {
			alert("제품번호를 입력하세요");
			f.p_num.focus();
			return;
		}
		if (f.p_name.value.trim().length <= 0) {
			alert("제품명을 입력하세요");
			f.p_name.focus();
			return;
		}
		if (f.p_company.value.trim().length <= 0) {
			alert("판매사를 입력하세요");
			f.p_company.focus();
			return;
		}
		if (f.p_price.value.trim().length <= 0) {
			alert("상품가격을 입력하세요");
			f.p_price.focus();
			return;
		}
		if (f.p_saleprice.value.trim().length <= 0) {
			alert("할인가를 입력하세요");
			f.p_saleprice.focus();
			return;
		}
		if (f.f_param_s.value.trim().length <= 0) {
			alert("s사진을 넣어주세요");
			f.f_param_s.focus();
			return;
		}
		if (f.f_param_l.value.trim().length <= 0) {
			alert("l사진을 넣어주세요");
			f.f_param_l.focus();
			return;
		}
		if (f.p_content.value.trim().length <= 0) {
			alert("상품내용을 입력하세요");
			f.p_content.focus();
			return;
		}
		
		f.action = "product_reg.do";
		f.submit();
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div id="login">
		<form method="post" encType="multipart/form-data">
			<table>
				<caption>상품등록</caption>
				<tbody>
					<tr>
						<th style="width: 30%" class="title">분류</th>
						<td><input type="radio" name="category" value="com001">컴퓨터
							<input type="radio" name="category" value="ele002">가전제품 <input
							type="radio" name="category" value="sp003">스포츠</td>
					</tr>
					<tr>
						<th class="title">제품번호</th>
						<td><input type="text" name="p_num" size="20" /></td>
					</tr>
					<tr>
						<th class="title">제품명</th>
						<td><input type="text" name="p_name" size="20" /></td>
					</tr>
					<tr>
						<th class="title">판매사</th>
						<td><input type="text" name="p_company" size="20" /></td>
					</tr>
					<tr>
						<th class="title">상품가격</th>
						<td><input type="text" name="p_price" size="20" /></td>
					</tr>
					<tr>
						<th class="title">할인가</th>
						<td><input type="text" name="p_saleprice" size="20" /></td>
					</tr>
					<tr>
						<th class="title">상품이미지S</th>
						<td><input type="file" name="f_param_s" /></td>
					</tr>
					<tr>
						<th class="title">상품이미지L</th>
						<td><input type="file" name="f_param_l" /></td>
					</tr>
					<tr>
						<th class="title">상품내용</th>
						<td><textarea name="p_content" id="p_content" cols="50"
								rows="8"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left;"><input
							type="button" value="상품등록" onclick="product_reg_go(this.form)" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>

