<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.s_gro_cs.model.*"%>
<%
S_gro_csVO s_gro_csVO = (S_gro_csVO) request.getAttribute("s_gro_csVO");
%>
<html>
<head><title>讀書會類別資料新增 - addS_gro_cs.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>讀書會類別資料新增 - addS_gro_cs.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>讀書會類別資料:</h3>
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

<FORM METHOD="post" ACTION="s_gro_cs.do" name="form1">
<table border="0">

	<tr>
		<td>類別名稱:</td>
		<td><input type="TEXT" name="cs_name" size="45" 
			value="<%= (s_gro_csVO==null)? "PHP" : s_gro_csVO.getCs_name()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>	


</body>
</html>