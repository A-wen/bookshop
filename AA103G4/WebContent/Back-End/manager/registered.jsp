<%@page import="com.fun.model.FunVO"%>
<%@page import="java.util.List"%>
<%@page import="com.fun.model.FunService"%>
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
<%
	FunService funSvc = new FunService();
	pageContext.setAttribute("funSvc", funSvc);
	List<FunVO> funlist = funSvc.getAll();
	pageContext.setAttribute("funlist", funlist);
%>
<jsp:useBean id="competenceSvc" scope="page" class="com.competence.model.CompetenceService" />
<jsp:useBean id="employeeService" scope="page" class="com.employee.model.EmployeeService" />
<style type="text/css">
	.startCss {
		text-align: center;
		white-space: nowrap;
	}
	.table>tbody>tr>td{
		vertical-align: middle;
	}
	.panel-danger>.panel-heading {
	    color: #333;
	    background-color: #f5f5f5;
	    border-color: #ddd;
	}
	.okColor{
		color:green;
	}
	.removeColor{
		color:red;
	}
	.table>tbody>tr>td,.table>tbody>tr>th{
		vertical-align: middle;
		background-color:#ddd;
		border-top:1px solid black;
		padding:6px;
	}
	h1.panel-title{
	text-align:center;
	font-family:微軟正黑體;
	}
</style>
<div class="row row-offcanvas row-offcanvas-left">
	<div class="container--fluid">
		<div class="row">
			<div class="col-xs-9 col-sm-9">
				<div class="panel-default">
					<div class="panel-danger" align="text-center">
						<div class="panel-heading">
							<div>
								<h1 class="panel-title"><b>員工姓名:${employeeVOED.empName}</b></h1>
							</div>
						</div>
						<div class="panel-heading">
							<div>
								<h1 class="panel-title"><b>員工帳號:${employeeVOED.empAcc}</b></h1>
							</div>
						</div>
					</div>
					<c:set var="ok"
						value="<h2><span class='glyphicon glyphicon-ok-sign okColor'></h2>" />
					<c:set var="no"
						value="<h2><span class='glyphicon glyphicon-remove-sign removeColor'></h2>" />
					<div width="10%">
						<table class="table" border='1px' ALIGN="center" WIDTH="30%">
							<tr align='center' valign='middle'>
								<th class="startCss"><b>員工姓名</b></th>
								<c:forEach var="funVO" items="${funlist}">
									<th class="startCss">${funVO.funName}</th>
								</c:forEach>
								<th class="startCss"><b>編輯</b></th>
								<th class="startCss"><b>編輯</b></th>
							</tr>
							<form method="post"
								action="<%=request.getContextPath()%>/emp/EmployeeServlet.do">
								<tr
									class="${employeeService.AjaxaccLogin(employeeVOED.getEmpAcc()).empno}"
									align='center' valign='middle'>
									<td class="startCss"><h4><b>${employeeVOED.empName}</b></h4></td>
									<c:forEach var="funVO" items="${funSvc.all}">
										<td class="${funVO.funno}"><input type="checkbox"
											style="display: none;" name="competence"
											value="${funVO.funno}"
											${competenceSvc.getEmpCompeteceFunNo(employeeService.AjaxaccLogin(employeeVOED.getEmpAcc()).empno).contains(funVO.funno)? "checked":""}>
											<div class="funcheck">${competenceSvc.getEmpCompeteceFunNo(employeeService.AjaxaccLogin(employeeVOED.getEmpAcc()).empno).contains(funVO.funno)?ok:no}</div></td>
									</c:forEach>
									<td class="startCss">
										<button class="startUpdate btn btn-primary"
											onclick="return false;"
											id="${employeeService.AjaxaccLogin(employeeVOED.getEmpAcc()).empno}">修改權限</button>
									</td>
									<td class="startCss"><input type="hidden" name="emp_no"
										value="${employeeService.AjaxaccLogin(employeeVOED.getEmpAcc()).empno}">
										<input type="hidden" name="action"
										value="UpdateEmpPersonCompetence"> <input
										type="submit" class="btn btn-primary test" value="送出修改">
									</td>
								</tr>
							</form>
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
				if ("${sweet}".length != 0) {
					swal("修改完成", "已修改", "success");
				}
			});
</script>
