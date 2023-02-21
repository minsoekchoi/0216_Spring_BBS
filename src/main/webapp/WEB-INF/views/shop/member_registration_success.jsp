<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
<style type="text/css">
#login table {
	width: 400px;
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
}

#login table td {
	text-align: left;
	border: 1px solid black;
	padding: 4px 10px;
}

.title {
	background: lightsteelblue;
}
</style>
<script type="text/javascript">
	function login_go(f) {
		f.action = "member_registration_success.do";
		f.submit();
	}
</script>
</head>
<body>
	<div id="login">
		<form method="post">
			<table>
				<caption>로그인</caption>
				<tbody>
					<tr>
						<th style="text-align: center;" class="title">회원가입을 축하합니다. 로그인 후 이용해주세요</th>
					</tr>
					<tr>
						<td style="text-align: center;">
							<input type="button" value="확인" onclick="login_go(this.form)" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>

