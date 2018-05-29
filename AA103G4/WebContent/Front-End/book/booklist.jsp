<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.book.model.*"%>
<%@ page import="com.booktype.model.*"%>

<jsp:useBean id="bookVO" scope="request" class="com.book.model.BookVO" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<!DOCTYPE html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品頁面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/css/all.css"	rel="stylesheet">
<title>商品頁面</title>



<style>
/* * { */
/* 	font-family: 微軟正黑體, Tahoma, Verdana; */
/* } */

body {
	background-color: #eee;
}

.list-group-item {
	font-size: 24px;
	text-align: center;
	margin-top: 10px;
}

a.list-group-item:hover {
	background-color: steelblue;
}
</style>

</head>
<body>
	<!-- header -->
	<div>
		<jsp:include page="/Front-End/header.jsp" />
	</div>
	<!-- header -->
	<!-- 幻燈片container-fluid -->	
	<div class="container-fluid">
		<!-- 幻燈片row -->
		<div class="row">
			<!-- 幻燈片置中區 -->
			<div style="margin: 0px 80px">
				<div id="carousel-id" class="carousel slide" data-ride="carousel">
					<!-- 幻燈片小圓點區 -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-id" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-id" data-slide-to="1" class=""></li>
					</ol>
					<!-- 幻燈片主圖區 -->
					<div class="carousel-inner">
						<div class="item active">
							<img src="<%=request.getContextPath()%>/Front-End/img/cm1.png" alt="好書不買嗎~~!!!" height="400">
							<div class="container">
								<div class="carousel-caption">
									<h1>好書不買嗎~~!!!</h1>
									<p>好書不買嗎~~!!!</p>
								</div>
							</div>
						</div>
						<div class="item">
							<img src="<%=request.getContextPath()%>/Front-End/img/cm2.png" alt="好書不買嗎~~!!!">
							<div class="container">
								<div class="carousel-caption">
									<h1>好書不買嗎~~!!!</h1>
									<p>好書不買嗎~~!!!</p>
								</div>
							</div>
						</div>
					</div>
					<!-- 上下頁控制區 -->
					<a class="left carousel-control" href="#carousel-id" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left"></span>
					</a>
					<a class="right carousel-control" href="#carousel-id" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
			</div>
			<!-- 幻燈片置中區 -->
		</div>
		<!-- 幻燈片row -->
	</div>
	<!-- 幻燈片container-fluid -->
	<!-- 主內容區container-fluid -->
	<div class="container-fluid" style="margin-top: 10px">
		<!-- 主內容區row -->
		<div class="row" style="margin-top: 10px">
			<!-- 主內容區-書籍類別-佔2 -->
			<div class="col-xs-12 col-sm-2">
				<div class="col-xs-12 col-sm-2"></div>
				<div class="col-xs-12 col-sm-10 text-center"
					style="padding: 10px; border: 1px black solid">
					<div class="list-group" Style="font-size:20px">
						<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
							<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=${booktypeVO.type_no}" class="list-group-item">${booktypeVO.type_name}</a>
						</c:forEach>
					</div>
				</div>
			</div>
			<!-- 主內容區-書籍類別-佔2 -->
			<!-- 主內容區-搜索區-佔8 -->
			<div class="col-xs-12 col-sm-8" style="padding: 10px">
				<!-- 主內容區-搜索區-container-fluid -->
				<div class="container-fluid">
					<!-- 主內容區-搜索區-row1 -->
					<div class="row" style="padding-bottom: 20px">
						<!--搜索表單 -->
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/Front-End/book/book.do" class="form-horizontal">
							<div class="col-sm-10">
								<div class="form-group">
									<div class="col-sm-3">
										<label for="book_name" class="control-label h4">書名</label>
										<input type="text" id="book_name" name="book_name" class="form-control h3" value="${querybookVO.book_name}">
									</div>
									<div class="col-sm-3">
										<label for="book_author" class="control-label h4">作者</label>
										<input type="text" id="book_author" name="book_author" class="form-control h3" value="${querybookVO.book_author}">
									</div>
									<div class="col-sm-3">
										<label for="type_no" class="control-label h4">類型</label>
										<select id="type_no" name="type_no" class="form-control h3">
											<option value="">請選擇
											<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
												<option value="${booktypeVO.type_no}" ${querybookVO.type_no==booktypeVO.type_no? 'selected' : ''}>${booktypeVO.type_name}
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-3">
										<label for="comp_no" class="control-label h4">出版社</label>
										<select id="comp_no" name="comp_no" class="form-control h3">
											<option value="">請選擇
											<c:forEach var="companyVO" items="${companySvc.allCompany}">
												<option value="${companyVO.comp_no}" ${querybookVO.comp_no==companyVO.comp_no? 'selected' : ''}>${companyVO.comp_name}
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-sm-2">
								<label for="go" class="control-label h3" style="color: #fff">　</label>
								<input type="submit" id="go"
									class="btn btn-primary form-control" value="查詢"> <input
									type="hidden" name="action" value="listBooks_ByCompositeQuery">
								<input type="hidden" name="from" value="Front_End">
							</div>
						</form>
						<!--搜索表單 -->
					</div>
					<!-- 主內容區-搜索區-row1 -->
					<!-- 主內容區-結果區-row2 -->
					<div class="row">
						<!-- 結果區 -->
						<div id="resultPage">
							<hr>
						<%
							if (request.getAttribute("bookList") != null) {
						%>
							<jsp:include page="searchResult.jsp" /> 
						<%
						}
						%>
						</div>
						<!-- 結果區 -->
					</div>
					<!-- 主內容區-結果區-row2 -->
				</div>
				<!-- 主內容區-搜索區-container-fluid -->
			</div>
			<!-- 主內容區-搜索區-佔8 -->
			<!-- 主內容區-廣告區-佔1 -->
			<div class="col-xs-12 col-sm-2">
			<% for(int i=0;i<4;i++){
				int y = (int)(Math.random()*100)+1001;
			%>
				<div><a href="<%=request.getContextPath()%>/Front-End/book/bookpage/<%=y%>"><img src="<%=request.getContextPath()%>/img?book_no=<%=y%>" style="width: 60%"><br><%=bookSvc.getOneBook(y).getBook_name()%></a></div>
			<%}%>
			</div>
			<!-- 主內容區-廣告區-佔1 -->
		</div>
		<!-- 主內容區row -->
	</div>
	<div style="margin-bottom:70px"></div>
	<!-- 主內容區container-fluid -->
	<!-- footer -->
	<div class="container-fluid">
		<jsp:include page="/Front-End/footer.jsp"></jsp:include>
	</div>
	<!-- footer -->
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<script>
$(function(){
	if("${notfound}".length!=0){swal("此條件查無資料", "請重新查詢。", "error");}
	$(".addtrack").click(function (){
		/*******
		*宣告emp_acc變數取值<--jquery選擇器
		*記得要寫在方法內不能會有，未定義的屬性。
		********/
		var mem_no2 = $(this).parent().children("input.mem_no").val();
		var book_no2 = $(this).parent().children("input.book_no").val();
		if (mem_no2.length==0){
			swal("尚未登入", "要先登入才能追蹤商品唷~", "error");
		}else{
		$.ajax({
			 type:"POST",
			 url:"<%=request.getContextPath()%>/Front-End/Track.do",
				data : {
					action : 'addTrack',
					mem_no : mem_no2,
					book_no : book_no2,
				},
				dataType : "json",
				success : function(data) {
					console.log(data);
					if (data.result == 'success') {
						swal("已加入追蹤", "可到會員專區查看", "success");
					}
					if(data.result == 'fail') {
						swal("你已經追蹤過囉~!!!");
					}
				},
				error : function() {
					console.log('error your json response not true,plz reset your contorller');
				}
			});
		}
	});
	$(".addcart").click(function (){
		/*******
		*宣告emp_acc變數取值<--jquery選擇器
		*記得要寫在方法內不能會有，未定義的屬性。
		********/
		var item2 = $(this).parent().children("input.item").val();
		$.ajax({
	 		type:"GET",
	 		url:"<%=request.getContextPath()%>/Front-End/Cart/update.do",
	 		data : {
	 			act : 'add',
	 			item : item2,
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
				if (data.result == 'success') {
					swal("已加入購物車", "可到購物車查看", "success");
				}
				if (data.result == 'fail') {
					swal("加入購物車", "可到購物車查看", "error");
				}
			},
			error : function() {
				console.log('error your json response not true,plz reset your contorller');
			}
		});
	});
})
</script>
</body>
</html>
