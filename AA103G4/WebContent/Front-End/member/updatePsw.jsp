<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Book思議 | 修改密碼</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />	
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>

<style type="text/css">
	.tips_true {
		color: green;
		font-size: 14px;
	}
	
	.tips_false {
		color: red;
		font-size: 14px;
	}
</style>
<script type="text/javascript">
	//驗證密碼
	function checkpsd1() {
		psd1 = form1.passWord.value;
		var reg1 = false;
		var reg2 = false;
		var reg3 = false;
		if (psd1.length<6 || psd1.length>10) {
			divpassword1.innerHTML = '<font class="tips_false">密碼長度必須6碼至10碼</font>';
		} else {
			for (i = 0; i < psd1.length; i++) {
				if ((psd1.charAt(i) >= 'A' && psd1.charAt(i) <= 'Z')
						|| (psd1.charAt(i) >= 'a' && psd1.charAt(i) >= 'a' && psd1
								.charAt(i) <= 'z')) {
					reg1 = true;
				} else if (psd1.charAt(i) >= '0' && psd1.charAt(i) <= '9') {
					reg2 = true;
				} else {
					reg3 = true;
				}
			}
			if (!reg1 || !reg2 || reg3) {
				divpassword1.innerHTML = '<font class="tips_false">密碼必須包含數字與字母</font>';
			} else {
				divpassword1.innerHTML = '<font class="tips_true">輸入格式正確</font>';
			}
		}
	}
	//驗證確認密碼
	function checkpsd2() {
		if (form1.passWord.value != form1.passWord2.value) {
			divpassword2.innerHTML = '<font class="tips_false">第二次的密碼有誤</font>';
		} else {
			divpassword2.innerHTML = '<font class="tips_true">輸入格式正確</font>';
		}
	}
</script>
<style type="text/css">
.mg {
	margin: 10px;
}
</style>
</head>
<body>
<jsp:include page="/Front-End/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<ol>${message}</ol>
							</c:forEach>
						</ul>
					</font>
				</c:if>
				<form action="<%=request.getContextPath()%>/member/MemServlet.do"
					  id="login_form" method="post" class="form-horizontal" name="form1">
					<div class="input-group input-group-lg">
						<font size='6'>${memVO.mem_name},您好</font>
					</div>
					<div>
						<hr style="border: 0; border-top: 1px hidden;">
					</div>
					<label for="account" class="control-label"><span id="writePW"><font size='5'>請在下方輸入新密碼</font></span></label>
					<div class="col-sm-12">
						<hr style="border: 0; border-top: 1px hidden;">
					</div>
					<p>

						<label for="passWord" class="control-label">新的密碼</label>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-lock"></span>
						</span> <input type="password" name="mem_psw" id="passWord"
							class="form-control" onblur="checkpsd1()">
					</div>
					<div>
						<span id="divpassword1"></span>
					</div>
					<label for="passWord2" class="control-label">確認新的密碼</label>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-lock"></span>
						</span> <input type="password" name="mem_psw2" id="passWord2"
							class="form-control" onblur="checkpsd2()">
					</div>
					<div>
						<span id="divpassword2"></span>
					</div>
					<p>
					<p>
					<div class="col-sm-12">
						<button class="btn btn-info form-control mg">
							<i class="glyphicon glyphicon-ok-sign"></i>送出
						</button>
					</div>
					<div class="col-sm-12">
						<a href="<%=request.getContextPath()%>/Front-End/login.jsp"
							class="btn btn-info form-control mg">取消</a> <input type="hidden"
							name="action" value="ResetMemPsw"> <input type="hidden"
							name="mem_mail" value="${memVO.mem_mail}">
					</div>
				</form>
			</div>
		</div>
	</div>

		<jsp:include page="/Front-End/footer.jsp"></jsp:include>
<script>
$().ready(function(){
	$('#writePW').click(function(){
		$('#passWord').val('a123456');
		$('#passWord2').val('a123456');	
	});
	

});

</script>
</body>
</html>