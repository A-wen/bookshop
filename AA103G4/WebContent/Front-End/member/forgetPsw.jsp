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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
    <title>忘記密碼</title>
    <style type="text/css">
    .mg {
        margin: 10px;
    }
    </style>
</head>

<body >
	<jsp:include page="/Front-End/header.jsp" />
	<br><br><br><br><br><br><br><br><br>
    <div class="container">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-xs-12 col-sm-6">
            
                <form action="<%=request.getContextPath()%>/member/MemServlet.do" id="login_form" method="post" class="form-horizontal">
                    <label for="account" class="control-label">
                        <span id="regEmail"><font size='5'>請輸入您的註冊信箱</font></span>
                        <font color='red'>(必填)</font>
                                         	    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font color='red'>:
            <ol>
                <c:forEach var="message" items="${errorMsgs}">
                    <ol>${message}</ol>
                </c:forEach>
            </ol>
        </font>
    </c:if>
                    </label>
                    <div class="col-sm-12">
                        <hr style="border: 0; border-top: 1px hidden;">
                    </div>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"> 
	   						<span class="glyphicon glyphicon-lock"></span>
                        </span>
                        <input type="email" name="mem_mail" id="account" class="form-control" ">
					</div>
					<div>	
						<span id="mail"></span>
					</div>
					<p>
					<div class="col-sm-12 ">
						<button class="btn btn-info form-control mg ">
							<i class="glyphicon glyphicon-ok-sign "></i>送出
						</button>
					</div>
					<div class="col-sm-12 ">
						<a href="<%=request.getContextPath()%>/Front-End/index.jsp" class="btn btn-info form-control mg">取消</a>
		                <input type="hidden" name="action" value="forgetMemberPsw">
		            </div>
                </form>
            </div>
        </div>
    </div>
    
    <jsp:include page="/Front-End/footer.jsp" />
    <script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(document).ready(
	    function test() {
	        $("#account").change(function() {
	            /*******
	             *宣告emp_acc變數取值<--jquery選擇器
	             *記得要寫在方法內不能會有，未定義的屬性。
	             ********/
	            var mem_mail2 = $("#account").val();
	            $.ajax({
	                type: "POST",
	                url: "<%=request.getContextPath()%>/member/MemServlet.do",
	                data: {
	                    action: 'checkForgetMail',
	                    mem_mail: mem_mail2,
	                },
	                dataType: "json",
	                success: function(data) {
	                    console.log(data);
	                    if (data.result == 'false') {
	                        $('#mail').html(
	                            '<font color="red">' + data.message + '</font>');
	                        $('#account').parent().removeClass(
	                            'has-success').addClass('has-error');
	                    } else {
	                        $('#mail').html('<font color="green">' + data.message + '</font>');
	                        $('#account').parent().removeClass('has-error').addClass('has-success');
	                    }
	                },
	                error: function() {
	                    console
	                        .log('error your json response not true,plz reset your contorller');
	                }
	            });
	        });
	    })
	</script>
</body>
<script>
$().ready(function(){
	$('#regEmail').click(function(){
		$('#account').val('java.aa103@gmail.com');
	});
});


</script>


</html>
