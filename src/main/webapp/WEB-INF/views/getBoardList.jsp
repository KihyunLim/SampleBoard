<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-3.3.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/getBoardList.js"/>"></script>
<title>목록 조회</title>
</head>
<body>

<center>
	<h1>게시글 목록</h1>
	<h3>${userName } 환영합니다.<a href="logout.do">Log-out</a></h3>
	
	<!-- 검색 시작 -->
	<%-- <form action="getBoardList.do" method="post">
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td align="right">
					<select name="searchCondition">
						<c:if test="${searchInfo.searchCondition eq 'CONTENT' }">
						<option value="TITLE">제목</option>
						<option value="CONTENT" selected>내용</option>
						</c:if>
						<c:if test="${searchInfo.searchCondition ne 'CONTENT' }">
						<option value="TITLE" selected>제목</option>
						<option value="CONTENT">내용</option>
						</c:if>
					<!-- 굳이 동적으로 표현할 필요없어보여 주석 처리 -->
					<c:forEach items="${conditionMap }" var="option">
						<option value="${option.value }">${option.key }</option>
					</c:forEach>
					</select>
					
					<c:choose>
						<c:when test="${searchInfo.searchKeyword ne '' }">
						<input name="searchKeyword" id="asd" type="text" value="${searchInfo.searchKeyword }" />
						</c:when>
						
						<c:otherwise>
						<input name="searchKeyword" id="asd" type="text" />
						</c:otherwise>
					</c:choose>
					
					<!-- <input name="searchKeyword" type="text" /> -->
					<input type="submit" id="btnSearch" value="<spring:message code="message.board.list.search.condition.btn"/>" />
				</td>
			</tr>
		</table>
	</form> --%>
	<!-- 검색 종료 -->
	
	<table id="tableBoardList" border="1" cellpadding="0" cellspacing="0" width="700">
		<thead>
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="100">작성자</th>
				<th bgcolor="orange" width="200">일자</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
		</thead>
		
		<%-- <tfoot>
			<tr>
				<td colspan="5" align="center">
					<c:if test="${pageMaker.prev }">
						<a href="getBoardList.do${pageMaker.makeQuery(pageMaker.startPage - 1) }">이전</a>
					</c:if>
				
					<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="page">
					<c:if test="${page eq searchInfo.curPage }">
					[${page }]
					</c:if>
					<c:if test="${page ne searchInfo.curPage }">
					<a href="getBoardList.do?page=${page }&searchCondition=${searchInfo.searchCondition}&searchKeyword=${searchInfo.searchKeyword}">[${page }]</a>
					</c:if>
					</c:forEach>
					
					<c:if test="${pageMaker.next }">
						<a href="getBoardList.do${pageMaker.makeQuery(pageMaker.endPage + 1) }">다음</a>
					</c:if>
				</td>
			</tr>
		</tfoot> --%>
	</table>
	<br>
	<input type="button" id="btnWrite" value="게시글 작성"></input>
</center>

</body>
</html>