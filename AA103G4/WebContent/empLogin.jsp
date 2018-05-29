<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book41管理|登入</title>
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<script>
$().ready(function(){
	//DEMO用新功能，點擊footer內的"Book41"與"本公司員工"就會填入預設帳號
	//填入admin帳號密碼用
	$('#testAdmin').click(function(){
		$('#account').val('book41');
		$('#passWord').val('book41');
	});
	//填入能上架書籍的帳號用(預設是後台新增員工帳號的java.aa103@gmail.com)
	$('#testEmp').click(function(){
		$('#account').val('java.aa103@gmail.com');
	});
    $("#account").change(function() {
        /*******
         *宣告emp_acc變數取值<--jquery選擇器
         *記得要寫在方法內不能會有，未定義的屬性。
         ********/
        var emp_acc2 = $("#account").val();
        var emp_psw2 = $("#passWord").val();
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/emp/EmployeeServlet.do",
            data: {
                action: 'checkLogin',
                emp_acc: emp_acc2,
                emp_psw: emp_psw2
            },
            dataType: "json",
            success: function(data) {
                /*********
                 *encodeURI 
                 *用此encodeURI() 會比使用 escape() 轉換 URI 正確。比較要注意的是這個方法不處理 ' (單引號)。
                 *decodeURI() 函數 (function) 將具有特殊符號編碼過的網址字串 (string) 參數 (parameter) 進行回復編碼，
                 *參數若是運算式 (expression) 
                 *則回傳運算式結果，若沒有參數則回傳 undefined ，另一個函數 encodeURI() 可進行相關編碼。
                 **********/
                console.log(data);
                if (data.result == 'false') {
                    $('#error')
                        .html('<font color="red">' + data.message + '</font>');
                    $('#account')
                        .parent()
                        .parent()
                        .removeClass('has-success')
                        .addClass('has-error');
                } else {
                    $('#error')
                        .html(
                            '<font color="green">' + data.message + '</font>');
                    $('#account')
                        .parent()
                        .parent()
                        .removeClass('has-error')
                        .addClass('has-success');
                }
            },
            error: function() {
                console.log('error your json response not true,plz reset your contorller');
                // 					 alert("登入失敗，請確認您的帳號密碼是否正確");
            }
        });
    });
}); 
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
	#main{
		background: url(<%=request.getContextPath()%>/Front-End/img/book2.jpg)no-repeat center center;
		background-size:cover;
		height:150px;
		}
	body{
		background-color:#E8E8E8;
	}
</style>
</head>
<body>
	<div class="container-fluid" id="main"></div>
	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<br>
				<div>
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">
							<div class="panel-heading text-center"  style="background-color:#D9D9D9;padding-bottom:10px">
								<div>
									<h1>Book41</h1>
								</div>
							</div>
						</div>
						<div class="col-sm-1"></div>
					</div>
					<br>
					<div class="panel panel-default">
						<div class="panel-heading" style="background-color:#D4D4D4">
							<div class="panel-body">
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
												class="form-control" placeholder="請輸入您的帳號">
										</div>
										<div>
											<span id='error'></span>
										</div>
										<p>
									</div>
									<div class="col-sm-12" style="margin-bottom:20px">
										<div class="input-group input-group-lg">
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-lock"></span>
											</span> <input type="password" name="emp_psw" id="passWord"
												class="form-control" placeholder="請輸入您的密碼">
											<p>
										</div>
									</div>
									<div>
											<div class="col-sm-2"></div>
											<div class="col-sm-8">											
												<button class="btn-lg btn-default form-control reg" style="height:45px">
													<i class="glyphicon glyphicon-ok-sign"></i>登入
												</button>
											</div>
											<div class="col-sm-2"></div>
										<input type="hidden" name="action" value="empLogin">
									</div>	
								</form>
							</div>
						</div>
					</div>
					<div class="col-sm-12 xhr">
						<P>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>

	<footer class="container-fluid text-center" style="position: fixed; bottom: 0; left: 0; right: 0">
	<p class="pager">©2016&<span id="testAdmin">Book41</span>，非<span id="testEmp">本公司員工</span>，請勿登入本公司網站。</p>
	</footer>

</body>
</html>