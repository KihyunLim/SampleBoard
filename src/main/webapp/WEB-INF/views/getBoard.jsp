<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-3.3.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/getBoard.js"/>"></script>
<script type="text/javascript">
	var getUserId = "${userId}";
</script>
<title>글 상세</title>
</head>
<body>
<a href="logout.do">Log-out</a>

<center>
	<h1>글 상세</h1>
	
	<hr>
	
	<a id="aDeleteBoard" href="deleteBoard.do">글삭제</a>&nbsp;&nbsp;&nbsp;
	<a id="aGetBoardList" href="getBoardList.do">글목록</a>
	
	<hr>
	
	<form>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left">
					<input class="myBoard" id="title" type="text" />
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left"><span id="writer"></span></td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left">
					<textarea class="myBoard" id="content" cols="40" rows="10"></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">등록일</td>
				<td align="left"><span id="regDate"></span></td>
			</tr>
			<tr>
				<td bgcolor="orange">조회수</td>
				<td align="left"><span id="cnt"></span></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="btnUpdate" value="글 수정" />
				</td>
			</tr>
		</table>
	</form>
	
	<hr>

	<table border="1" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>
					<textarea id="replyConent" cols="40" rows="3"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" id="btnInsertReply" value="댓글등록">
				</td>
			</tr>
		</tbody>
	</table>
	
	<br>
	
	<table border="1" cellpadding="0" cellspacing="0">
		<tbody id="tbodyReplyList">
		</tbody>
	</table>

	</center>

</body>
</html>