<%@page import="com.fun.model.FunVO"%>
<%@page import="java.util.List"%>
<%@page import="com.fun.model.FunService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<% 
	FunService funSvc = new FunService();
	pageContext.setAttribute("funSvc", funSvc);
	List<FunVO> funlist = funSvc.getAll();
	pageContext.setAttribute("funlist", funlist);
	%>
	<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>被include的網頁</title>
</head>
<style type="text/css">
#startCss{
text-align:center;
display:block;
visibility:hidden; 
}
#cssTable{
border:hidden;
display:block;
}
</style>
<body>
<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService"/>
				<div class="container">
					<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-xs-12">
				<table class="table" border='1' width='800' id="cssTable">
					<tr align='center' valign='middle'>
						<td class="startCss">${employeeSvc.getOneEmp(emp_no).getEmpName()}</td>
						<td class="startCss">${employeeSvc.getOneEmp(emp_no).getEmpAcc()}</td>
						<c:forEach var="competenceVO" items="${byEmpNoList}">
						<td>${funSvc.getOneFun(competenceVO.getFunNo()).getFunName()}</td>
						</c:forEach>
					</tr>
				</table>
					</div>
						</div>
							</div>

</body>
</html>