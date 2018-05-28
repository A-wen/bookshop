<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>結帳</title>
    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.css" >
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" >

    <!-- Lobibox CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css"	>
</head>
<body>
	<jsp:include page="/Front-End/header.jsp" />
	<jsp:include page="/Front-End/footer.jsp" />
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
	<script>
		$().ready(function(){
			Lobibox.alert("success", {
				title: '提示',
				msg: "交易成功！",
				buttons: {
					ok: {
				        'class': 'btn btn btn-success',
				        text: '確定',
				        closeOnClick: true
				    }
				},
				callback: function(){
					window.location.replace("<%=request.getContextPath()%>/Front-End/member/memTransion.jsp");
				}
			});
		});
	</script>
</body>
</html>