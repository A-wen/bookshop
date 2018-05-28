<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	PmService promotionsSvc = new PmService();
	List<PmVO> list = promotionsSvc.getAllPm();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<!DOCTYPE html>
<html>
<head>
<title>所有活動資料 - listAllPm.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' bordercolor='#CCCCFF'
		width="800">
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td colspan="13">
				<h3>所有活動資料 - listAllPm.jsp</h3> <a
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


	<table border='1' bordercolor='#CCCCFF' width="800">
		<tr>
			<th>活動編號</th>
			<th>書號</th>
			<th>刪除</th>
		</tr>
		<c:forEach var="pmVO" items="${list}"  >
			<tr align='center' valign='middle' >
				<!--將修改的那一筆加入對比色而已-->
				<td>${pmVO.pd_no}</td>

				<td><c:forEach var="bookVO" items="${bookSvc.allBook}">
						<c:if test="${bookVO.book_no==pmVO.book_no}">
	                    ${bookVO.book_no}<br>【${bookVO.book_name}】
                    </c:if> </c:forEach></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/pm/pm.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="pd_no" value="${pmVO.pd_no}">
						<input type="hidden" name="book_no" value="${pmVO.book_no}">
						<input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="action" value="delete_Pm">
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
