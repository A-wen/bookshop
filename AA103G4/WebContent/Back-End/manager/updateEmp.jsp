<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String url = null;
	try {
		url = request.getParameter("url");
		if (url.length() == 0) {
			url = "index-inner.jsp";
		}
	} catch (NullPointerException e) {
		url = "index-inner.jsp";
	}
%>
<style type="text/css">
#clot {
	color: #fa0;
}

.tips_true {
	color: green;
	font-size: 14px;
}

.tips_false {
	color: red;
	font-size: 14px;
}
</style>
	<!--/col-->
	<div class="row row-offcanvas row-offcanvas-left">
		<div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">
			<!-- 內頁的位置-->
			<div class="container-fluid">
				<div class="row">
	<div class="col-xs-7 col-sm-7">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font color='red'>
								<ol>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ol>
							</font>
						</c:if>

						<form
							action="<%=request.getContextPath()%>/emp/EmployeeServlet.do"
							method="post" class="form-horizontal">


							<div class="col-sm-12">
								<div class="panel-defalut">
									<div class="panel-heading" style="background-color:#D9D9D9;">
										<div>
											<h5 class="panel-title">
												<font size="16">${employeeVO.empName}</font>您好，以下為您的修改密碼欄位
											</h5>
<%-- 											<font color="#1e90ff" size=3px>${employeeVO.empName}員工</font> --%>
										</div>
									</div>
									<input type="hidden" name="emp_name"
										value="${employeeVO.empName}" class="form-control">
									<p>
								</div>


								<label for="passWord" class="control-label">修改密碼</label>
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-lock"></span>
									</span> <input type="password" name="emp_psw" id="passWord"
										class="form-control" placeholder="請輸入修改密碼" value=""
										onblur="checkpsd1()">
									<p>
								</div>

								<label for="passWord2" class="control-label">密碼修改確認</label>
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-lock"></span>
									</span> <input type="password" name="emp_psw2" id="passWord2"
										class="form-control" placeholder="請重新輸入修改密碼" value=""
										onblur="checkpsd2()">
									<p>
								</div>
								<p>
								<div>
									<button class="btn btn-info">
										<i class="glyphicon glyphicon-ok-sign"></i>送出
									</button>
								</div>
								<input type="hidden" name="action" value="updateEmp">
							</div>
						</form>
					</div>
				</div>
			</div>


			<!-- 內頁的位置-->
		</div>
		<div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
	</div>
	<!--/main col-->
<!--/.container-->


<script>
	$(document).ready(function() {
		if("${sweet}".length!=0){swal("修改完成", "已成功", "success");}
		var emp_psw = $('input[name="emp_psw"]').val();
		var emp_psw2 = $('input[name="emp_psw2"]').val();
		if (emp_psw != emp_psw2) {
			alert('密碼錯誤，請重新輸入');
		}

	})
</script>
