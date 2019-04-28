<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-3.3.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/login.js"/>"></script>
<title>로그인</title>
</head>
<body>

<center>
	<h1>로그인</h1>
	<hr>

	<form>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange">아이디 : </td>
				<td><input type="text" name="userId" value="${userVO.userId }"/></td>
			</tr>
			<tr>
				<td bgcolor="orange">패스워드 : </td>
				<td><input type="password" name="password" value="${userVO.password }"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="btnLogin" value="로그인"/>
				</td>
			</tr>
		</table>
	</form>
</center>

</body>
</html>