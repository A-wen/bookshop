<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pd.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	PdService pdSvc = new PdService();
	List<PdVO> list = pdSvc.getAllPd();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html><html>
<head>
<title>所有活動資料 - listAllPd.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' bordercolor='#CCCCFF'
		width="1600">
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td colspan="13">
				<h3>所有活動資料 - listAllPd.jsp</h3> <a
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
			<th>活動編號</th>
			<th>活動名稱</th>
			<th>活動敘述</th>
			<th>起始日期</th>
			<th>結束日期</th>
			<th>折扣</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<c:forEach var="pdVO" items="${list}" >
			<tr align='center' valign='middle'
				${(pdVO.pd_no==param.pd_no) ? 'bgcolor=#CCCCFF':''}>
				<!--將修改的那一筆加入對比色而已-->
				<td>${pdVO.pd_no}</td>
				<td>${pdVO.pd_name}</td>
				<td>${pdVO.pd_desc}</td>
				<td>${pdVO.startdate}</td>
				<td>${pdVO.enddate}</td>
				<td>${pdVO.discount}</td>
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/pd/pd.do">
						<input type="submit" value="修改">
						<input type="hidden" name="pd_no" value="${pdVO.pd_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="action" value="getOnePd_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/pd/pd.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="pd_no" value="${pdVO.pd_no}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="action" value="delete_Pd">
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
