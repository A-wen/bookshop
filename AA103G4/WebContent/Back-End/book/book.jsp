<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.book.model.*"%>
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Book41-商品管理-書籍管理</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert-dev.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backhome.css" />
<script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
<script>
	$(function() {
		if("${addBook}".length!=0){swal("新增完成", "已新增", "success");}
		if("${updateBook}".length!=0){swal("修改完成", "已修改", "success");}
		if("${deleteBook}".length!=0){swal("刪除完成", "已刪除", "error");}
		if("${deletefail}".length!=0){swal("刪除失敗", "","error");}
		if("${notfound}".length!=0){swal("此條件查無資料", "請重新查詢。", "error");}
		$('.text').click(function(){
			var book_no =$(this).attr("id");
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/Front-End/book/bookpage",
				data : {
					action : 'BookDesc',
					book_no : book_no,
				},
				dataType : "json",
				success : function(data) {
				console.log(data);
				Lobibox.window({
				title: '書籍敘述',
				content:data.desc
				});
				},
				error : function() {
				console.log('omg');
				}
				});
			})
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
			<div class="col-xs-4 col-sm-4 col-md-3 col-lg-2" id="sidebar"
				role="navigation">
				<!-- Sidebar的位置-->
				<%@ include file="/Back-End/back-sidebar.jsp"%>
				<!-- Sidebar的位置-->
			</div>

			<div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">
				<!-- 內頁的位置-->
				
				<div role="tabpanel">
					<!-- 標籤面板：標籤區 -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="h3 ${param.action=='query'? 'active' :''}"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">查詢書籍</a></li>
						<li role="presentation" class="h3 ${param.action=='add'? 'active' :''}"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">新增書籍</a></li>
						<li role="presentation" class="h3 ${param.action=='update'? 'active' :''}"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">修改書籍</a></li>
					</ul>
					<!-- 標籤面板：內容區 -->
					<div class="tab-content">
						<br>
						<div role="tabpanel" class="tab-pane ${param.action=='query'? 'active' :''}" id="tab1">
							<jsp:include page="searchBook.jsp" />
						</div>
						<div role="tabpanel" class="tab-pane ${param.action=='add'? 'active' :''}" id="tab2">
							<jsp:include page="addBook.jsp"></jsp:include>
						</div>
						<div role="tabpanel" class="tab-pane ${param.action=='update'? 'active' :''}" id="tab3">
						<%
							if (request.getAttribute("updateBookVO") != null) {
						%>
								<jsp:include page="updateBook.jsp" />
						<%
						}else{
						%>
						<div class="h2">
							<font style="color:red;margin-left:30px">
							目前沒有書籍需要修改
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

