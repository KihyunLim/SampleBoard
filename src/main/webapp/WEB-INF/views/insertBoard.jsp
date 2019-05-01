<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-3.3.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/insertBoard.js"/>"></script>
<title>글 등록</title>
</head>
<body>

<center>
	<h1>글 등록</h1>
	<a href="logout.do">Log-out</a>
	<hr>
	<form action="insertBoard.do" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left">
					<input name="title" type="text" />
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left">
					<input name="writer" type="text" size="10" value=${userId } readonly />
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left">
					<textarea name="content" cols="40" rows="10"></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70">업로드</td>
				<td align="left">
					<input type="file" name="uploadFile"></input>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="새글 등록" id="btnRegist" />
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="getBoardList.do">글 목록가기</a>
</center>

</body>
</html>