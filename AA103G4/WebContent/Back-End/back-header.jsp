<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#header {
	position: fixed;
	right: 0;
	left: 0;
	top: 0;
}
</style>

<nav id="header" class="navbar navbar-dark bg-primary navbar-fixed-top">
	<a id="back-home" class="navbar-brand"
		href="<%=request.getContextPath()%>/Back-End/index.jsp"><i
		class="glyphicon glyphicon-home"></i> Book41 後台管理</a>
	<div class="pull-right">
		<form action="<%=request.getContextPath()%>/emp/EmployeeServlet.do"
			method="post">
			<button id="log-out" class="btn-info" type="submit">
				<i class="glyphicon glyphicon-log-out"></i>登出
			</button>
			<input type="hidden" name="action" value="logout">
		</form>
	</div>
	</div>
</nav>
<hr>
