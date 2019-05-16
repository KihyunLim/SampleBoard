<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-3.3.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/getBoardList.js"/>"></script>
<title>목록 조회</title>
</head>
<body>

<center>
	<h1>게시글 목록</h1>
	<h3>${userName } 환영합니다.<a href="logout.do">Log-out</a></h3>
	
	<!-- 검색 시작 -->
	<form action="getBoardList.do" method="post">
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td align="right">
					<select id="selSearchCondition">
						<option value="ALL" selected>전체</option>
						<option value="TITLE">제목</option>
						<option value="CONTENT">내용</option>
					</select>
					
					<input type="text" id="inpSearchKeyword" />
					
					<input type="button" id="btnSearch" value="검색" />
				</td>
			</tr>
		</table>
	</form>
	<!-- 검색 종료 -->
	
	<table border="1" cellpadding="0" cellspacing="0" width="700">
		<thead>
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="100">작성자</th>
				<th bgcolor="orange" width="200">일자</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
		</thead>
		
		<tbody id="tbodyBoardList"></tbody>
		
		<tfoot id="tfootPageWrap">
			<tr>
				<td colspan="5" align="center">
					<a id="aPrev" class="aPaging" href="">이전</a>
					<span id="spanPageWrap">
						<!-- <a href="getBoardList.do"></a> -->
					</span>				
					<a id="aNext" class="aPaging" href="">다음</a>
				</td>
			</tr>
		</tfoot>
	</table>
	<br>
	<input type="button" id="btnWrite" value="게시글 작성"></input>
</center>

</body>
</html>