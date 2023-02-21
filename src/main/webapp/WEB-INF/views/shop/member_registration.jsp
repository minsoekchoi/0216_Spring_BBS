<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<style type="text/css">
#login table {
	width: 300px;
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
	function registration_ok_go(f) {
		if (f.m_id.value.trim().length <= 0) {
			alert("아이디를 입력하세요");
			f.m_id.focus();
			return;
		}
		if (f.m_pw.value.trim().length <= 0) {
			alert("비밀번호를 입력하세요");
			f.m_pw.focus();
			return;
		}
		if (f.m_name.value.trim().length <= 0) {
			alert("이름을 입력하세요");
			f.m_name.focus();
			return;
		}
		if (f.m_addr.value.trim().length <= 0) {
			alert("주소를 입력하세요");
			f.m_addr.focus();
			return;
		}
		f.action = "member_registration_ok.do";
		f.submit();
	}
	function login_go(f) {
		f.action = "login.do";
		f.submit();
	}
</script>
</head>
<body>
	<div id="login">
		<form method="post" encType="multipart/form-data">
			<table>
				<caption>회원가입</caption>
				<tbody>
					<tr>
						<th style="width: 30%" class="title">아이디</th>
						<td><input type="text" name="m_id" size="20" /></td>
					</tr>
					<tr>
						<th class="title">패스워드</th>
						<td><input type="password" name="m_pw" size="20" /></td>
					</tr>
					<tr>
						<th class="title">이름</th>
						<td><input type="text" name="m_name" size="20" /></td>
					</tr>
					<tr>
						<th class="title">주소</th>
						<td><input type="text" name="m_addr" size="20" /></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
						<input type="button" value="회원가입" onclick="registration_ok_go(this.form)" />
						<input type="button" value="로그인창" onclick="login_go(this.form)" />
						<input type="reset" value="취소" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>

