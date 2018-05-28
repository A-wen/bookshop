<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC >
<html>
<head>
<title>Book思議 | 會員登入</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />	
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<script type="text/javascript">
	
</script>
<style type="text/css">
.reg {
	margin: 10px;
}

.xhr {
	text-align: center;
}
/* .container{ */
/* min-height:760px; */
/* } */
</style>
</head>
<body>
<jsp:include page="/Front-End/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<div class="panel-default ">
					<div class="panel-heading">
						<h3 class="panel-title text-center" id="title">
								<font size="12">會員登入</font>
						</h3>
			  		</div>
				</div>
				<form method="post" name="myForm1" action="<%=request.getContextPath()%>/member/MemServlet.do">
					<div class="col-sm-12" style="margin-top:40px">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font color='red' id="msgs">
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
							</font>
						</c:if>
						<br>
					</div>
					<div class="col-sm-12" style="margin-top:20px">
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-envelope"></span>
							</span> <input type="text" name="mem_mail" id="account"
								class="form-control" onchange='valid()' placeholder="請輸入您的帳號(電子郵件信箱)">
						</div>
						<div class="input-group input-group-lg" style="margin-top:20px">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span> 
							<input type="password" name="mem_psw" id="passWord"
								   class="form-control" placeholder="請輸入您的密碼">
						</div>
						<div class="col-sm-12" style="margin-top:40px">
							<button class="btn btn-info form-control reg">
								<span class="glyphicon glyphicon-ok-sign"></span>登入
							</button>
						</div>
						<input type="hidden" name="action" value="memlogin">
				</form>
				<div class="col-sm-12 xhr">
					<a
						href="<%=request.getContextPath()%>/Front-End/member/forgetPsw.jsp"
						class="btn btn-info form-control reg">忘記密碼?</a>
					<P>
						<a href="<%=request.getContextPath()%>/Front-End/member/register.jsp" class="btn btn-info form-control reg">點我加入會員</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/Front-End/footer.jsp" />
	<script>
	$().ready(function(){
		$('#title').click(function(){
			$('#account').val('java.aa103@gmail.com');
			$('#passWord').val('a123456');
		});
	});
	</script>
</body>
</html>
