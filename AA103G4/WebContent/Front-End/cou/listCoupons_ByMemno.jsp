<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<%-- <jsp:useBean id="listCoupons_ByMemno" scope="request" type="java.util.Set" /> --%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<%
Integer mem_no = Integer.parseInt(request.getParameter("mem_no"));
Set<CouponVO> set = memSvc.getCouponsByMemno(mem_no);
pageContext.setAttribute("listCoupons_ByMemno", set);
%>

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
		<th>消費金名稱</th>
		<th>消費金內容</th>
		<th>消費金起始日</th>
		<th>消費金截止日</th>
		<th>消費金金額</th>		
	</tr>
	</thead>
	<tbody>	
	<c:forEach var="couVO" items="${listCoupons_ByMemno}" >
		<tr align='center' valign='middle'><!--將修改的那一筆加入對比色而已-->
			<td>${couVO.couname}</td>
			<td>${couVO.couexp}</td>
			<td>${couVO.coustart}</td>
			<td>${couVO.couend}</td>
			<td>${couVO.coudis}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<%-- <jsp:include page="/Front-End/footer.jsp"></jsp:include> --%>
</body>
</html>

