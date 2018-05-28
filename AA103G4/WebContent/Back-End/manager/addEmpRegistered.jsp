<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<%--  <c:if test="${not empty employeeVOED}"> --%>
<!--  swal("新增員工", "已完成", "success"); -->
<%--  </c:if> --%>
<div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">
	<!-- 				內頁的位置 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-7 col-sm-7">
				<div class="panel-default">
					<div class="panel-heading" style="background-color:#D9D9D9;">
						<div>
							<h3 class="panel-title">
								<font size=12>新增<span id="testEmp">員工</span></font>
							</h3>
						</div>
					</div>
					<c:if test="${not empty errorMsgs}">
						<font color='red' id="msgs">
							<ol>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ol>
						</font>
					</c:if>
					<form action="<%=request.getContextPath()%>/emp/EmployeeServlet.do"
						id="login_form" method="post" class="form-horizontal"
						name="regform">

						<label for="emp_name" class="control-label">員工姓名</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" name="emp_name" id="emp_name"
								class="form-control"
								value="${(employeeVO==null)?employeeVO.empName:''}">
						</div>

						<label for="emp_acc" class="control-label">員工帳號</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-envelope"></span>
							</span> <input type="email" name="emp_acc" id="emp_acc"
								class="form-control" value="${(employeeVO==null)?employeeVO.empAcc:''}">
						</div>
						<input type="hidden" name="action" value="addEmpRegistered">
						<br>
						<button class="btn btn-info" type="submit"><i class="glyphicon glyphicon-ok-sign"></i>送出</button>
<!-- 						<button class="btn btn-danger magic" onclick="return false;">神奇小按鈕</button> -->
					</form>
				</div>
			</div>
						
			<!-- 內頁的位置-->
		</div>
		<div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
	</div>
	<!--/main col-->
</div>
<!--/.container-->
<script>
	$().ready(function() {
		if("${employeeVOED}".length!=0){swal("新增完成", "已成功", "success");}
		$('#testEmp').click(function() {
			$('input[name="emp_name"]').val("陳XX");
			$('input[name="emp_acc"]').val("java.aa103@gmail.com");
		})

	})
</script>

