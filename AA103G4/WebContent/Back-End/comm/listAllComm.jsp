<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.comm.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CommService commSvc = new CommService();
	List<CommVO> list = commSvc.getAllComm();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService"/>
<!DOCTYPE html>
<html>
<head>
<title>所有評論 - listAllComm.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' bordercolor='#CCCCFF'
		width="1600">
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td colspan="13">
				<h3>所有評論 - listAllComm.jsp</h3> <a
				href="<%=request.getContextPath()%>/select_page.jsp"><img
					src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>


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


	<table border='1' bordercolor='#CCCCFF' width="1600">
		<tr>
			<th width="90">評論編號</th>
			<th width="150">書籍</th>
			<th width="50">評論會員</th>
			<th width="50">評論內容</th>
			<th width="50">評論等級</th>
			<th width="50">日期</th>
			<th width="40">刪除</th>
		</tr>
		<c:forEach var="commVO" items="${list}">
			<tr align='center' valign='middle' ${(commVO.book_no==param.book_no) ? 'bgcolor=#CCCCFF':''}>
				<!--將修改的那一筆加入對比色而已-->
				<td>${commVO.comm_no}</td>
				<td><c:forEach var="bookVO" items="${bookSvc.allBook}">
						<c:if test="${commVO.book_no==bookVO.book_no}">
	                    [${bookVO.book_no}]-${bookVO.book_name}
                    </c:if>
					</c:forEach></td>
					
				<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
					<td><c:forEach var="memVO" items="${memSvc.all}">
						<c:if test="${commVO.mem_no==memVO.memNo}">
						[${memVO.memNo}]-${memVO.memName}
						</c:if>
					</c:forEach>
					</td> 
				<td>${commVO.comm_desc}</td>
				<td>${commVO.comm_level}</td>
				<td>${commVO.comm_date}</td>

				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/comm/comm.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="comm_no" value="${commVO.comm_no}">
						<input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="action" value="delete_Comm">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br>本網頁的路徑:
	<br>
	<b> <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
		<font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%>
	</b>
</body>
</html>
