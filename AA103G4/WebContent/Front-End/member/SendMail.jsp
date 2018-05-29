<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.mem.model.*"%> --%>
<%-- <%@ page import="java.io.*"%> --%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />	
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<title>忘記密碼</title>
	<!-- 共用(Footer)的CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
</head>
<body>
<jsp:include page="/Front-End/header.jsp"></jsp:include>
	<br><br><br><br><br><br><br><br><br>
		<div class="container">
			<div class="row">
<!-- 				<div class="col-sm-3"></div> -->
				<div class="col-xs-12 col-sm-6 col-sm-offset-3">
					<font size='6'>你的密碼重設連結已經發出！</font>
			 		<div class="col-sm-12">
					<hr style="border: 0; border-top: 1px hidden;">
					</div>
					<p>已經將密碼重設的確認信發送至您的信箱. 請於限定的時間內點擊該連結，來進行重設密碼的操作。</p>
			 	</div>
			 </div>
		</div>
			 		<jsp:include page="/Front-End/footer.jsp"></jsp:include>
</body>
</html>
