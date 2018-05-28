<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>

<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
	<title>Book思議 | 註冊頁面</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css"> --%>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css"> --%>
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script> --%>

	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
	<style type="text/css">
	.tips_true {
		color: green;
		font-size: 14px;
	}
	.tips_false {
		color: red;
		font-size: 14px;
	}
	#reg {
		color: blue;
		font-size: 14px;
		text-shadow: 0px 0px 15px #FF37FD;
	}
	</style>

</head>
<body>
	<jsp:include page="/Front-End/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<div class="panel-default" >
					<div class="panel-heading">
						<h3 class="panel-title text-center">
							<font size="12">會員註冊</font>
						</h3>
			  		</div>
				</div>
				<form action="<%=request.getContextPath()%>/member/MemServlet.do" id="login_form"
					  method="post" enctype="multipart/form-data" class="form-horizontal"
					  name="regform" style="margin-top:20px">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
					<font color='red' id="msgs">請修正以下錯誤:
						<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
						</ul>
					</font>
					</c:if>
						<label for="account" class="control-label">電子郵件信箱</label> 
						<div class="input-group input-group-lg">
						<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-envelope"></span>
						</span>
						<input type="email" name="mem_mail" id="account" class="form-control" value="${memVO.mem_mail}" >
						</div>
						<div>
							<span id="mail"></span>
						</div>
					
						<label for="userName" class="control-label">姓名</label> 
						<div class="input-group input-group-lg">
						<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-user"></span>
						</span>
						<input type="text" name="mem_name" id="userName" class="form-control" value="${memVO.mem_name}">
						</div>
						
						<label for="userNic" class="control-label">暱稱</label> 
						<div class="input-group input-group-lg">
						<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-user"></span>
						</span>
						<input type="text" name="mem_nic" id="userNic" class="form-control" value="${memVO.mem_nic}">
						</div>
						<div>
						<span id="nic"></span>
						</div>
						<label for="phoneNumber" class="control-label">電話</label>
						<div class="input-group input-group-lg">
						<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-earphone"></span>
						</span>
						 <input type="text" name="mem_tel" id="phoneNumber" class="form-control" value="${memVO.mem_tel}">
						</div>
						<div>
						<span id="phone"></span>
						</div>
					
					

						
						
						<label for="passWord" class="control-label">密碼</label> 
						<div class="input-group input-group-lg">
						<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-lock"></span>
						</span>
						<input type="password" name="mem_psw" id="passWord" class="form-control" value="${memVO.mem_psw}" onblur="checkpsd1()">
						</div>
						<div>	
						<span id="divpassword1"></span>
						</div>
						
						
					
						<label for="passWord2" class="control-label">確認密碼</label> 
						<div class="input-group input-group-lg">
						<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-lock"></span>
						</span>
						<input type="password" name="mem_psw2" id="passWord2" class="form-control" onblur="checkpsd2()">
						</div>
						<div>
						<span id="divpassword2"></span>
						</div>
					

						
						<label for="memPhoto" class="control-label">大頭貼</label> 
						<div class="input-group input-group-lg">
							<span class="input-group-addon"><span class="glyphicon glyphicon-picture"></span>
							</span>
							<input type="file" name="mem_photo" id="memPhoto" class="form-control">
						</div>


					<input type="hidden" name="action" value=register>


					<div class="col-sm-12 text-center" style="margin-top:80px">
						<button class="btn btn-info btn-lg" type="submit">
							<i class="glyphicon glyphicon-ok-sign"></i>送出
						</button>
					<a href="<%=request.getContextPath()%>/Front-End/index.jsp" class="btn btn-info">取消</a>
					<button type="button" id="magic" class="btn btn-danger">點</button>
					</div>
			</div>
			</form>
		</div>
		</div>
	</div>
	<div style="margin-top:100px"></div>
	
	<jsp:include page="/Front-End/footer.jsp" />

	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
	<!-- bootstrap-filestyle 讓上傳檔案框好看一點的東西 -->
	<script src="<%=request.getContextPath()%>/js/bootstrap-filestyle.min.js"></script>
	<script type="text/javascript">
$(document).ready(
		
		
		function test(){
			$(":file").filestyle({buttonName: "btn-primary",badge: false,buttonText: "選擇檔案上傳",size: "lg"});
		 $("#account").change(function (){
			 var mem_mail2=$("#account").val();
			$.ajax({
				 type:"POST",
				 url:"<%=request.getContextPath()%>/member/MemServlet.do",
					data : {
						action : 'checkRegisteredMail',
						mem_mail : mem_mail2,
					},
					dataType : "json",
					success : function(data) {
						/* console.log(data); */
						if (data.result == 'false') {
							$('#mail').html(
									'<font color="green">'+data.message+'</font>');
							$('#account').parent().removeClass('has-error').addClass('has-success');
						} else {
							$('#mail').html('<font color="red">'+data.message+'</font>');
							$('#account').parent().removeClass(
							'has-success').addClass('has-error');
							
						}
					},
					error : function() {
						console
								.log('error your json response not true,plz reset your contorller');
					}
				});
		 });
	 $("#userNic").change(function (){
	 var userNic2=$("#userNic").val();
	$.ajax({
		 type:"POST",
		 url:"<%=request.getContextPath()%>/member/MemServlet.do",
			data : {
				action : 'checkRegisteredNic',
				mem_nic : userNic2,
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
				if (data.result == 'false') {
					$('#nic').html(
							'<font color="green">'+data.message+'</font>');
					$('#userNic').parent().removeClass('has-error').addClass('has-success');
				} else {
					$('#nic').html('<font color="red">'+data.message+'</font>');
					$('#userNic').parent().removeClass(
					'has-success').addClass('has-error');
					
				}
			},
			error : function() {
				console
						.log('error your json response not true,plz reset your contorller');
			}
		});
 });
	 	$("#phoneNumber").change(function (){
		var phoneNumber2=$("#phoneNumber").val();
		$.ajax({
		 type:"POST",
	 	 url:"<%=request.getContextPath()%>/member/MemServlet.do",
		 data : {
		 action : 'checkRegisteredPhone',
		 mem_tel : phoneNumber2,
		 },
		 dataType : "json",
		 success : function(data) {
		 console.log(data);
		 if (data.result == 'false') {
		 $('#phone').html('<font color="green">'+data.message+'</font>');
		 $('#phoneNumber').parent().removeClass('has-error').addClass('has-success');
		} else {
		$('#phone').html('<font color="red">'+data.message+'</font>');
		$('#phoneNumber').parent().removeClass(
		'has-success').addClass('has-error');				
			}
		},
		error : function() {
			console
					.log('error your json response not true,plz reset your contorller');
		}
	});
});
		 })
	
//驗證密碼
function checkpsd1() {
	psd1 = regform.passWord.value;
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
	if (regform.passWord.value != regform.passWord2.value) {
		divpassword2.innerHTML = '<font class="tips_false">第二次的密碼有誤</font>';
	} else {
		divpassword2.innerHTML = '<font class="tips_true">輸入格式正確</font>';
	}
}

$('#magic').click(function(){
	$('input[name="mem_mail"]').val("java.aa103@gmail.com");	
	$('input[name="mem_nic"]').val("小帥哥");	
	$('input[name="mem_psw"]').val("a123456");	
	$('input[name="mem_psw2"]').val("a123456");	
	$('input[name="mem_name"]').val("林嘉晉");	
	$('input[name="mem_tel"]').val("0956181155");	
})
</script>
</body>
</html>
