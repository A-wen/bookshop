<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有追蹤清單 - listAllTrack.jsp</title>
</head>
<body>
<body bgcolor='white'>
	<table border='1' cellpadding='5' cellspacing='0' bordercolor='#CCCCFF' width="1000">
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td colspan="13">
				<h3>所有追蹤清單 - listAllTrack.jsp</h3> 
				<a href="<%=request.getContextPath()%>/Back-End/index.jsp">
				<img src="<%=request.getContextPath()%>/Back-End/img/back.png" width="30" border="0">回首頁</a>
			</td>
		</tr>
<!-- 	</table> -->
		<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
<!-- <table border='1' bordercolor='#CCCCFF' width='800'> -->
	<tr>
		<th>追蹤會員</th>
		<th>追蹤書籍</th>
		<th>刪除</th>

	</tr>
	<c:forEach var="trackVO" items="${trackList}"  >
		<tr align='center' valign='middle'>
			<td>
				<c:forEach var="memVO" items="${memList}">
					<c:if test="${trackVO.mem_no==memVO.mem_no}">
						[${memVO.mem_no}]-${memVO.mem_name}
					</c:if>
				</c:forEach>
			</td> 
			<td>
				<c:forEach var="bookVO" items="${bookList}">
					<c:if test="${trackVO.book_no==bookVO.book_no}">
	                    [${trackVO.book_no}]-${bookVO.book_name}
                    </c:if>
				</c:forEach></td>	
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/track/track.do">
					<input type="submit" value="刪除">
					<input type="hidden" name="mem_no" value="${trackVO.mem_no}">
					<input type="hidden" name="book_no" value="${trackVO.book_no}">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="delete_Track">
					</FORM>
				</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>