<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.competence.model.CompetenceService"%>
<%@page import="com.fun.model.FunVO"%>
<%@page import="com.competence.model.CompetenceVO"%>
<%@page import="com.fun.model.FunService"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.util.*"%>
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
<!DOCTYPE html>
<%
	FunService funSvc = new FunService();
	pageContext.setAttribute("funSvc", funSvc);
	List<FunVO> funlist = funSvc.getAll();
	pageContext.setAttribute("funlist", funlist);
	List<EmployeeVO> emplist = new EmployeeService().getAll();
	pageContext.setAttribute("emplist", emplist);
%>

<jsp:useBean id="competenceSvc" scope="page" class="com.competence.model.CompetenceService" />
<html>
<head>
<meta charset="utf-8">
<title>Book41 後台管理</title>
<meta name="generator" content="Codeply">

<%-- <script src="<%=request.getContextPath()%>/js/backbtnjquery.js"></script> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backhome.css" />
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<style type="text/css">
.startCss {
 	text-align: center; 
	vertical-align: middle;
	white-space: nowrap;
}
#extraCss{
 	text-align: center; 
	vertical-align: middle;
	white-space: nowrap;
}
.table>tbody>tr>td,.table>tbody>tr>th{
	vertical-align: middle;
	background-color:#ddd;
	border-top:1px solid black;
	padding:6px;
}
.panel-default>.panel-heading{
margin-top: -35px;
}
.okColor{
color:green;
}
.removeColor{
color:red;
}
</style>
<script>
	$(window).ready(
			function() {
				$(".startUpdate").click(
						function() {
							if ($(this).html() == "修改權限") {
								$(this).html("取消修改");
							} else {
								$(this).html("修改權限");
							}
							$("tr[class=" + $(this).attr("id") + "]").find(
									"input[type=checkbox]").toggle();
							$("tr[class=" + $(this).attr("id") + "]").find(
									"div.funcheck").toggle();
						});
				if("${sweet}".length!=0){swal("修改完成", "已修改", "success");}
				if("${deleteALL}".length!=0){swal("清除完成", "已修改", "success");}
				if("${deleteEmployee}".length!=0){swal("刪除完成", "已刪除", "success");}
				});
				
</script>
</head>
<body>
	<!-- Header的位置-->
	<%@ include file="/Back-End/back-header.jsp"%>
	<!-- Header的位置-->
	<div class="container-fluid" id="main">
		<!--/col-->
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
			<div class="col-xs-4 col-sm-4 col-md-3 col-lg-2" id="sidebar" role="navigation">
<!-- 				Sidebar的位置 -->
				<%@ include file="/Back-End/back-sidebar.jsp"%>
				<!-- Sidebar的位置-->
			</div>

			<div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">
				<!-- 內頁的位置-->
					<div class="container-fluid">
		<div class="row">
				<div class="col-sm-12">
			<div class="panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-users"></i><h1>員工資料權限</h1>
					</h3>
				</div>
				<c:set var="ok" value="<h2><span class='glyphicon glyphicon-ok-sign okColor'></span></h2>" />
				<c:set var="no" value="<h2><span class='glyphicon glyphicon-remove removeColor'></span></h2>" />
				<table class="table" border='1px' ALIGN="center" WIDTH="80%">
					<tr align='center' valign='middle'>
						<th class="startCss"><i class="fa fa-user"></i>員工姓名</th>
						<c:forEach var="funVO" items="${funlist}">
							<th class="startCss">${funVO.funName}</th>
						</c:forEach>
						<th class="startCss">編輯</th>
						<th class="startCss">編輯</th>
						<th class="startCss">編輯</th>
						<th class="startCss">編輯</th>
					</tr>
					<c:forEach var="employeeVO" items="${emplist}">
						<form method="post" action="<%=request.getContextPath()%>/emp/EmployeeServlet.do" >
							<tr class="${employeeVO.empno}" align='center' valign='middle'>
								<td class="startCss"><b>${employeeVO.empName}</b></td>
								<c:forEach var="funVO" items="${funSvc.all}">
									<td class="${funVO.funno} Css"><input type="checkbox"
										style="display: none;" name="competence"
										value="${funVO.funno}"
										${competenceSvc.getEmpCompeteceFunNo(employeeVO.empno).contains(funVO.funno)? "checked":""}>
									<div class="funcheck">${competenceSvc.getEmpCompeteceFunNo(employeeVO.empno).contains(funVO.funno)?ok:no}</div></td>
								</c:forEach>
								<td class="startCss">
									<button class="startUpdate btn btn-primary"
										onclick="return false;" id="${employeeVO.empno}">修改權限</button>
								</td>
								<td class="startCss"><input type="hidden" name="emp_no"
									value="${employeeVO.empno}"> <input type="hidden"
									name="action" value="UpdateCompetence"> <input
									type="submit" class="btn btn-primary test" value="送出修改">
								</td>
						</form>
						<td class="startCss">
							<form
								action="<%=request.getContextPath()%>/emp/EmployeeServlet.do"
								method="post">
								<input type="hidden" name="emp_no" value="${employeeVO.empno}">
								<input type="hidden" name="action" value="delete"> <input
									type="submit" class="btn btn-danger test1" value="清除權限"></input>
							</form>
						</td>
						<td class="startCss">
							<form
								action="<%=request.getContextPath()%>/emp/EmployeeServlet.do"
								method="post">
								<input type="hidden" name="emp_no" value="${employeeVO.empno}">
								<input type="hidden" name="action" value="deleteEmp"> <input
									type="submit" class="btn btn-danger" value="移除員工"></input>
							</form>
						</td>
					</c:forEach>
					</tr>
				</table>
			</div>
		</div>
	</div>
	</div>
				<!-- 內頁的位置-->
			</div>
			<div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
		</div>
		<!--/main col-->
	</div>
	<!--/.container-->
	<footer class="container-fluid">
		<!-- footer的位置-->
		<%@ include file="/Back-End/back-footer.jsp"%>
		<!-- footer的位置-->
	</footer>

</body>
</html>
