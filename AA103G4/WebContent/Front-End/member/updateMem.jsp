<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
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
/* .container{ */
/* min-height:750px; */
/* } */
</style>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book41會員修改頁面</title>
	
</head>
<body>
<jsp:include page="/Front-End/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<font size=12>會員資料修改</font>
					<h4><b>${(memVO.memName != null) ?  memVO.memName : param.memName}您好</b></h4>
					<img src="<%=request.getContextPath()%>/member/ReaderPhoto.do?mem_no=${memService.checkMemMailRepeat(memVO.memMail).memNo}"
						width="300" height="260" border="0">
				<form name="form1"
					action="<%=request.getContextPath()%>/member/MemServlet.do"
					method="post" enctype="multipart/form-data" class="form-horizontal">
					<div class="col-sm-12">
						<input type="hidden" name="mem_no" value="${memService.checkMemMailRepeat(memVO.memMail).memNo}">
						<input type="hidden" name="mem_name" value="${memVO.memName}">
						<input type="hidden" name="mem_nic" value="${memVO.memNic}">
						<input type="hidden" name="mem_mail" value="${memVO.memMail}">
					</div>

					<label for="phoneNumber" class="control-label">電話</label>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-earphone"></span>
						</span> <input type="text" name="mem_tel" id="phoneNumber"
							class="form-control"
							value="<%=((MemVO) (request.getAttribute("memVO")) == null)
					? ""
					: ((MemVO) request.getAttribute("memVO")).getMem_tel()%>"
							placeholder="請輸入想要修改的電話號碼">
					</div>
					<div>
						<span id="phone"></span>
					</div>

					<label for="passWord" class="control-label">修改密碼</label>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-lock"></span>
						</span> <input type="password" name="mem_psw" id="passWord"
							class="form-control"
							value="${(memVO==null)?memVO.memPsw:param.memPsw}"
							onblur="checkpsd1()">
					</div>
					<div>
						<span id="divpassword1"></span>
					</div>

					<label for="passWord2" class="control-label">確認修改密碼</label>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-lock"></span>
						</span> <input type="password" name="mem_psw2" id="passWord2"
							class="form-control" onblur="checkpsd2()">
					</div>
					<div>
						<span id="divpassword2"></span>
					</div>
						<label for="memPhoto" class="control-label">更換大頭貼</label>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-picture"></span>
						</span> <input type="file" name="mem_photo" id="memPhoto"
							class="form-control">
					</div>
					<br>
					<div class="col-sm-12">
						<button class="btn btn-info">
							<i class="glyphicon glyphicon-ok-sign" align='center'></i>送出修改
						</button>

						<a href="<%=request.getContextPath()%>/Front-End/index.jsp"
							class="btn btn-info">取消</a><br>
					</div>
					<input type="hidden" name="action" value="updateMember">
			</div>
			</form>
			</div>
			
		</div>
	
	<script>
	$(document).ready(
		function test(){
		 $("#phoneNumber").change(function (){
			 /*******
		 		 *宣告emp_acc變數取值<--jquery選擇器
		 		 *記得要寫在方法內不能會有，未定義的屬性。
		 		 ********/
			 var mem_tel2=$("#phoneNumber").val();
			$.ajax({
				 type:"POST",
				 url:"<%=request.getContextPath()%>/member/MemServlet.do",
															data : {
																action : 'checkRegisteredPhone',
																mem_tel : mem_tel2,
															},
															dataType : "json",
															success : function(
																	data) {
																console
																		.log(data);
																if (data.result == 'false') {
																	$('#phone')
																			.html(
																					'<font color="green">'
																							+ data.message
																							+ '</font>');
																	$(
																			'#phoneNumber')
																			.parent()
																			.removeClass(
																					'has-error')
																			.addClass(
																					'has-success');
																} else {
																	$('#phone')
																			.html(
																					'<font color="red">'
																							+ data.message
																							+ '</font>');
																	$(
																			'#phoneNumber')
																			.parent()
																			.removeClass(
																					'has-success')
																			.addClass(
																					'has-error');

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
					divpassword1.innerHTML = '<font class="tips_true">輸入格式正確�</font>';
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
	<div class='text-center'>
			<jsp:include page="/Front-End/footer.jsp"></jsp:include>
			</div>
</body>
</html>