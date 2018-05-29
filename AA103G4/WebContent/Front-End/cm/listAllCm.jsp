<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.cm.model.*"%>
<%@ page import="java.io.*"%>
<%
    CmService cmSvc = new CmService();
	List<CmVO> list = cmSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="CmService" scope="page" class="com.cm.model.CmService" />
<center>
<html>
<head>
</head>
<body bgcolor='white'>
<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>		
		<th>廣告照片</th>		
	</tr>
	<c:forEach var="cmVO" items="${list}">
		<tr align='center' valign='middle'>			
		<td><img src="<%=request.getContextPath()%>/cm/CMReader.do?cm_no=${cmVO.cmNo}"  width="400px" height="400px"></td>			 			
		</tr>
	</c:forEach>
</table>
</body>
</html>
</center>
