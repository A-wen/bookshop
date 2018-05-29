<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.book.model.*"%>
<%@ page import="com.comm.model.*"%>
<%@ page import="com.booktype.model.*"%>
<%@ page import="java.util.List"%>

<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="commSvc" scope="page" class="com.comm.model.CommService" />
<jsp:useBean id="bookVO" scope="request" class="com.book.model.BookVO" />
<jsp:useBean id="booktypeSvc" scope="request" class="com.booktype.model.BooktypeService" />

<!DOCTYPE html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Book思議 | ${bookVO.book_name}</title>
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">

<style>
* {
	font-family: 微軟正黑體, Tahoma, Verdana;
}

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
.star{
	color:#FFD700;
	font-size:30px;
}

</style>
</head>
<body>
<jsp:include page="/Front-End/header.jsp"></jsp:include>
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
	<div class="container-fluid" style="margin-top: 10px">
		<div class="row" style="margin-top: 10px">
			<div class="col-xs-12 col-sm-2">
				<div class="col-xs-12 col-sm-2"></div>
					<div class="col-xs-12 col-sm-10 text-center" style="padding: 10px; border: 1px black solid">
					<div class="list-group" font-size="20">
						<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
							<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=${booktypeVO.type_no}" class="list-group-item">${booktypeVO.type_name}</a>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-8" style="padding: 10px">
				<div class="container-fluid">
					<div class="row"
						style="border-bottom: #aaa 3px dotted; padding-bottom: 20px">
						<div class="col-xs-12 col-sm-3 col-md-2 col-lg-2">
							<img src="<%=request.getContextPath()%>/img?book_no=${bookVO.book_no}" style="width: 100%">
						</div>
						<div class="col-xs-12 col-sm-6 h2">
							<div class="row">
								<div class="col-sm-3 text-right">書名：</div>
								<div class="col-sm-9">${bookVO.book_name}</div>
							</div>
							<div class="row" style="margin-top:20px">		
								<div class="col-sm-3 text-right">定價：</div>
								<div class="col-sm-9">${bookVO.book_price}元</div>
							</div>
							<div class="row" style="margin-top:20px">	
								<div class="col-sm-3 text-right">作者：</div>
								<div class="col-sm-9">${bookVO.book_author}</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-3 text-left">
							<div class="col-sm-12 h3">
								<input type="hidden" class="mem_no" name="mem_no" value="${memVO.mem_no}">
								<input type="hidden" class="book_no" name="book_no" value="${bookVO.book_no}">
								<button class="btn-primary img-rounded addtrack h3" style="width:90%">
									<i class="glyphicon glyphicon-heart"></i><b>加入追蹤</b>
								</button>
							</div>
							<div class="col-sm-12 h3">
								<div>
									<input type="hidden" class="item" name="item" value="${bookVO.book_no}:1">
									<button class="btn-primary img-rounded addcart h3" style="width:90%">
										<i class="glyphicon glyphicon-ok-sign"></i><b>放入購物車</b>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="h3">
					<h2><b>商品介紹：</b></h2>
						${bookVO.book_desc}
					</div>
				</div>
				<div class="row" style="margin-top:50px">
					<div class="h3">
					
					<h2><b>看過的人說：</b></h2>
					<c:forEach var="commVO" items="${commSvc.getCommByBook(bookVO.book_no)}">
						<div class="row" style="margin-top:20px">
							<div class="col-sm-3">
							<c:forEach var="i" begin="0" end="5">
								<c:if test="${commVO.comm_level > i}">
									<span class="glyphicon glyphicon-star star"></span>
								</c:if>
								<c:if test="${commVO.comm_level < i}">
									<span class="glyphicon glyphicon-star-empty star"></span>
								</c:if>
							</c:forEach>
							</div>
							<div class="col-sm-6">會員${commVO.mem_no}說：${commVO.comm_desc}</div>
							<div class="col-sm-3">日期:${commVO.comm_date}</div>
						</div>
					</c:forEach>
					</div>
				</div>

			</div>

			<div class="col-xs-12 col-sm-2">
			<% for(int i=0;i<4;i++){
				int y = (int)(Math.random()*100)+1001;
			%>
				<div><a href="<%=request.getContextPath()%>/Front-End/book/bookpage/<%=y%>"><img src="<%=request.getContextPath()%>/img?book_no=<%=y%>" style="width: 60%"><br><%=bookSvc.getOneBook(y).getBook_name()%></a></div>
			<%}%>
			</div>
		</div>
	</div>
	<div style="margin-top:100px"></div>
	<jsp:include page="/Front-End/footer.jsp"></jsp:include>
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
	<script>
$(function(){
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
