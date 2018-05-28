<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.info.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    InformationService infoSvc = new InformationService();
	List<InformationVO> list = infoSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<jsp:useBean id="InformationService" scope="page" class="com.info.model.InformationService"/>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/book41.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/landing-page.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/font-awesome.css"	rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<title>Book思議</title>
	<jsp:include page="/Front-End/header.jsp"></jsp:include>
</head>
<body >


<table class="table table-striped table-hover">
  <thead>
    <tr>		
		<th>公告標題</th>		
		<th>公告日期</th>
		<th>公告內容</th>
	</tr>
	</thead>
  <tbody>	
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="infoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(infoVO.infono==param.infono) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->			
			<td>${infoVO.infotitle}</td>
			<td>${infoVO.infoterm}</td>
			<td>${infoVO.infoexp}</td>
			</tr>
	</c:forEach>
	</tbody>
</table>
			
<link href="<%=request.getContextPath()%>/css/info.css" rel="stylesheet">

	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>

