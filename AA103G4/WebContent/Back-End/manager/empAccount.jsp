<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<html>
<head>
<style type="text/css">
.parent{
margin-top:-55px;
}
</style>
<meta charset="utf-8">
<title>Book41 後台管理</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert-dev.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backhome.css" />
</head>
<body>
	<!-- Header的位置-->
	<%@ include file="/Back-End/back-header.jsp"%>
	<!-- Header的位置-->
	<div class="container-fluid" id="main">
		<!--/col-->
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
			<div class="col-xs-4 col-sm-4 col-md-3 col-lg-2" id="sidebar"
				role="navigation">
				<!-- Sidebar的位置-->
				<%@ include file="/Back-End/back-sidebar.jsp"%>
				<!-- Sidebar的位置-->
			</div>

			<div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">
				<!-- 內頁的位置-->
				<h1>Book41-員工帳號管理</h1>
				<hr>
				<div role="tabpanel">
					<!-- 標籤面板：標籤區 -->
					<ul class="nav nav-tabs parent" role="tablist">
						<li role="presentation" class="${param.action=='add'? 'active' :''} h3"><a href="#tab1"
							aria-controls="tab1" role="tab" data-toggle="tab">新增員工</a></li>
						<li role="presentation" class="${param.action=='update'? 'active' :''} h3"><a
							href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">修改密碼</a></li>
						<li role="presentation" class="${param.action=='finshed'? 'active' :''} h3"><a
							href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">權限配發</a></li>
					</ul>
					<!-- 標籤面板：內容區 -->
					<div class="tab-content">
						<br>
						<div role="tabpanel"
							class="tab-pane ${param.action=='add'? 'active' :''}" id="tab1">
							<jsp:include page="addEmpRegistered.jsp" />
						</div>
						<div role="tabpanel"
							class="tab-pane ${param.action=='update'? 'active' :''}"
							id="tab2">
							<jsp:include page="updateEmp.jsp" />
						</div>

						<div role="tabpanel"
							class="tab-pane ${param.action=='finshed'? 'active' :''}"
							id="tab3">
							<%
								if (request.getAttribute("employeeVOED") != null) {
							%>
							<jsp:include page="registered.jsp" />
							<%
								} else {
							%>
							<div class="h4">
								<font style="color: red"> 目前沒有員工可以配發權限 </font>
							</div>
							<%
								}
							%>
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
	<div style="position: fixed; bottom: 0; left: 0; right: 0">
	<footer class="container-fluid">
		<!-- footer的位置-->
		<%@ include file="/Back-End/back-footer.jsp"%>
		<!-- footer的位置-->
	</footer>
	</div>
</body>
</html>