<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>員工登入頁面</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
// 	var queryString={emp_acc:emp_acc,emp_psw:emp_psw};
$(document).ready(
		function test(){
		 $("#account").change(function (){
			 		 /*******
			 		 *宣告emp_acc變數取值<--jquery選擇器
			 		 *記得要寫在方法內不能會有，未定義的屬性。
			 		 ********/
			 var emp_acc2=$("#account").val();
			 var emp_psw2=$("#passWord").val();
			$.ajax({
				 type:"POST",
				 url:"<%=request.getContextPath()%>/emp/EmployeeServlet.do",
					data : {
						action : 'checkLogin',
						emp_acc : emp_acc2,
						emp_psw : emp_psw2
					},
					dataType : "json",
					success : function(data) {
						/*********
						*encodeURI 
						*用此encodeURI() 會比使用 escape() 轉換 URI 正確。比較要注意的是這個方法不處理 ' (單引號)。
						*decodeURI() 函數 (function) 將具有特殊符號編碼過的網址字串 (string) 參數 (parameter) 進行回復編碼，
						*參數若是運算式 (expression) 
						*則回傳運算式結果，若沒有參數則回傳 undefined ，另一個函數 encodeURI() 可進行相關編碼。
						**********/
						console.log(data);
						if (data.result == 'false') {
							$('#error').html(
									'<font color="red">'+data.message+'</font>');
							$('#account').parent().parent().removeClass(
									'has-success').addClass('has-error');
						} else {
							$('#error').html('<font color="green">'+data.message+'</font>');
							$('#account').parent().parent().removeClass('has-error').addClass('has-success');
						}
					},
					error : function() {
						console
								.log('error your json response not true,plz reset your contorller');
						// 					 alert("登入失敗，請確認您的帳號密碼是否正確");
					}
				});
		 });
	
		 })
		
	
 
</script>

<style type="text/css">
.reg {
	margin: 10px;
}

.xhr {
	text-align: center;
}

#error {
	vertical-align: bottom;
}
</style>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<h3>員工登入:</h3>
				<form method="post" name="myForm1" id="login_form"
					class="form-horizontal"
					action="<%=request.getContextPath()%>/emp/EmployeeServlet.do">
					<div class="col-sm-12">
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

						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-envelope"> </span>
							</span><input type="text" name="emp_acc" id="account"
								class="form-control" placeholder="請輸入您的帳號" value="book41">
						</div>
						<div>
							<span id='error'></span>
						</div>
						<p>
					</div>
					<div class="col-sm-12">
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-lock"></span>
							</span> <input type="password" name="emp_psw" id="passWord"
								class="form-control" placeholder="請輸入您的密碼" value="book41">
							<p>
						</div>
						<div class="col-sm-12">
							<button class="btn-success form-control reg">
								<i class="glyphicon glyphicon-ok-sign"></i>登入
							</button>
						</div>
						<input type="hidden" name="action" value="empLogin">
				</form>
				<div class="col-sm-12 xhr">
					<P>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>