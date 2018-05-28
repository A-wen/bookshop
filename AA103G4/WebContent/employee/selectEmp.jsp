<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>後台查詢員工首頁</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<script src="../../js/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function test(){
			 $("#empno").click(function(){
				 var emp_no=$("#emp_no").val();
				$.ajax({
					 type:"POST",
					 url:"<%=request.getContextPath()%>/emp/EmployeeServlet.do",
						data : {
							action : 'finyByEmpNo',
							emp_no : emp_no,
						},
						dataType : "text",
						success : function(data) {
						$('#ajaxResponseCss').html(data);
							},
						error : function() {
							console
									.log('error your json response not true,plz reset your contorller');
						}
					});
			 });
			 })
	</script>
<body>
			<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />
			<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunService" />
	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-xs-12 col-sm-8">
						<br><br><br><br><br><br><br><br><br><br><br>
			<h2>後台員工管理</h2>
			<a href='<%=request.getContextPath()%>/back/manager/index.jsp' class="btn btn-danger">員工首頁</a>
			<a href='<%=request.getContextPath()%>/back/manager/listAllEmp.jsp' class="btn btn-primary">所有員工</a>
			<a href='<%=request.getContextPath()%>/back/manager/addEmpRegistered.jsp' class="btn btn-primary">新增員工</a>
						<br>
						<br>
			<input type="text" name="emp_no" id="emp_no">
			<Button id ="empno">搜尋</Button>
			
			<div id="ajaxResponseCss">
<%-- 			<jsp:include page="SelectResult.jsp"></jsp:include> --%>
             </div>
			
					</div>
             		    	</div>
								</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="../../js/bootstrap.min.js"></script>
</body>
<script>
// $(window).ready(function(){
// 	$("#empno").click(function(){
// 		$('#ajaxResponseCss').show();
// 	});
// });
	
</script>
</html>





					
<!-- 						<div class="col-sm-12"> -->
<!-- 						<ol> -->
<!-- 							<FORM METHOD="post" -->
<%-- 								ACTION="<%=request.getContextPath()%>/emp/EmployeeServlet.do"> --%>
<!-- 								<i class="glyphicon glyphicon-hand-right"></i>:<b>選擇員工編號:</b> <select -->
<!-- 									size="1" name="emp_no"> -->
<%-- 									<c:forEach var="employeeVO" items="${empSvc.all}"> --%>
<%-- 										<option value="${employeeVO.getEmpno()}">${employeeVO.getEmpno()} --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 								<button class="btn btn-info" type="submit"> -->
<!-- 									<i class="glyphicon glyphicon-ok-sign"></i>送出 -->
<!-- 								</button> -->
<!-- 								<input type="hidden" name="action" value="getOne_For_Emp"> -->
<!-- 							</FORM> -->
<!-- 						</ol> -->
<!-- 						<p> -->
<!-- 					</div> -->
<!-- 							<FORM METHOD="post" -->
<%-- 								ACTION="<%=request.getContextPath()%>/emp/EmployeeServlet.do"> --%>

<!-- 								<i class="glyphicon glyphicon-hand-right"></i>:<b>選擇員工姓名:</b> <select -->
<!-- 									size="1" name="emp_no"> -->
<%-- 									<c:forEach var="employeeVO" items="${empSvc.all}"> --%>
<%-- 										<option value="${employeeVO.getEmpno()}">${employeeVO.getEmpName()} --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 								<button class="btn btn-info" type="submit"> -->
<!-- 									<i class="glyphicon glyphicon-ok-sign"></i>送出 -->
<!-- 								</button> -->
<!-- 								<input type="hidden" name="action" value="getOne_For_Emp"> -->
<!-- 							</FORM> -->