<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
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
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backhome.css" />
<style type="text/css">
	.startCss {
}
	.table>tbody>tr>td, .table>tfoot>tr>td, .table>thead>tr>td {
		padding:6px;
		text-align: center;
		vertical-align:middle;
		border-top-width:0px;
		border-top:1px solid black;
		background-color:#ddd;
	}
	
	td.startCss {
		border:1px solid black;
	}
	.startCss{
		text-align:center;
	}
	.container{
		min-height:200px;
	}
	.panel-default{
		border-color:transparent;
	}
	.panel-title{
		font-size: 30px;
	}

</style>
<meta charset="utf-8">
<title>Book41 後台管理</title>

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
				<div class="container">
					<div class="row">
						<%@ include file="pages/page1.file"%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="glyphicon glyphicon-user"></i>Book41會員資料
								</h3>
							</div>
						</div>
							<table class="table table-hover table-striped" cellpadding="0"  cellspacing="0">
								<tr class="info">
<!-- 									<th class="startCss">會員編號</th> -->
									<th class="startCss">會員大頭貼</th>
									<th class="startCss">會員暱稱</th>
									<th class="startCss">會員姓名</th>
									<th class="startCss">會員帳號</th>
									<th class="startCss">會員電話</th>
								</tr>
								<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<td class="startCss"><img
											src="<%=request.getContextPath()%>/member/ReaderPhoto.do?mem_no=${memVO.mem_no}"
											width="120" height="100" border="0"></td>
										<td class="startCss">${memVO.mem_nic}</td>
										<td class="startCss">${memVO.mem_name}</td>
										<td class="startCss">${memVO.mem_mail}</td>
										<td class="startCss">${memVO.mem_tel}</td>
									</tr>
								</c:forEach>
							</table>
							<%@ include file="pages/page2.file"%>
						
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
			<!-- footer的位置 -->
			<%@ include file="/Back-End/back-footer.jsp"%>
			<!-- footer的位置 -->
		</footer>



</body>
</html>