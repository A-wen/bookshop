<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.info.model.*"%>
<jsp:useBean id="infoSvc" scope="page" class="com.info.model.InformationService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Book41 公告管理</title>
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert-dev.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap-datepicker.zh-TW.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backhome.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-datepicker3.min.css" />





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
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>
				<div role="tabpanel">
					<!-- 標籤面板：標籤區 -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation"  class="${param.action=='all'? 'active' :''}"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">公告清單</a></li>
						<li role="presentation"  class="${param.action=='add'? 'active' :''}"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">新增公告</a></li>
						<li role="presentation"  class="${param.action=='update'? 'active' :''}"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">修改公告</a></li>
												
					</ul>
					<!-- 標籤面板：內容區 -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane ${param.action=='all'? 'active' :''}" id="tab1">
							<jsp:include page="listAllInfo.jsp" />
						</div>
						
						<div role="tabpanel" class="tab-pane ${param.action=='add'? 'active' :''}" id="tab2">
							<jsp:include page="addInfo.jsp"></jsp:include>
						</div>
						
						<div role="tabpanel" class="tab-pane ${param.action=='update'? 'active' :''}" id="tab3">
						<%
							if (request.getAttribute("infoVO") != null) {
						%>
								<jsp:include page="update_info_input.jsp" />
						<%
						}else{
						%>
						<div class="h2">
							<font style="color:red;margin-left:30px">
							目前沒有需要修改
							</font>
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
	<footer class="container-fluid">
		<!-- footer的位置-->
		<%@ include file="/Back-End/back-footer.jsp"%>
		<!-- footer的位置-->
	</footer>


</body>
</html>