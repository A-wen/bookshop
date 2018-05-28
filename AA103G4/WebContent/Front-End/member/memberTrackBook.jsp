<%@page import="com.book.model.BookService"%>
<%@page import="com.book.model.BookVO"%>
<%@page import="com.track.model.TrackVO"%>
<%@page import="com.track.model.TrackService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="memService" scope="session" class="com.mem.model.MemService"/>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	TrackService trackSvc = new TrackService();
	List<TrackVO> trackList = trackSvc.getTrackByMem(memService.checkMemMailRepeat(memVO.getMem_mail()).getMem_no());
	pageContext.setAttribute("trackList", trackList);
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>


<jsp:useBean id="bookService" scope="page"
	class="com.book.model.BookService" />
<jsp:useBean id="bookVO" scope="page" class="com.book.model.BookVO" />
	<c:forEach var="trackVO" items="${trackList}">
	<c:set var="bookVO" value="${bookService.getOneBook(trackVO.book_no)}" />
	</c:forEach>
<head>
<title>您的追蹤商品</title>
<jsp:include page="/Front-End/header.jsp" />
<script>
// <c:if test="${not empty sweet}">
// swal("以新增購物車", "已修改", "success");
// </c:if>
	$(document).ready(function(){
		if("${sweet}".length!=0){swal("清除完成", "已修改", "success");}
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
	       
	        $('.addCar').each(function() {
	            $(this).click(function() {
	                //因為按鈕跟書號,數量..不是同一層，所以要先回到上一層在找才找的到
	                var currentTR = $(this).closest('tr');
	                var currentBNo = $(this).closest('tr').find('input[name="item"]').val();
					var mem_no = $('input[name="mem_no"]').val();
					var book_no = $('input[name="book_no"]').val();
					var requestURL = $('input[name="requestURL"]').val();
	                  $.ajax({
	                	  type:"POST",
	                	  url:"<%=request.getContextPath()%>/member/AddCar.do",
	                	  data:{
	                		  action:"add",
	                		  item:currentBNo,
	                	  },
	                	  dataType :"text",
	                	  success : function(data){
	                		  $.ajax({
	              				type:"POST",
	              				url:"<%=request.getContextPath()%>/Front-End/Track.do",
	              				data : {
	              					action : 'delete_Track',
	              					book_no : book_no,
	              					mem_no : mem_no,
	              					requestURL:requestURL
	              				},
	              				dataType : "text",
	              				success : function(data) {
	              					currentTR.hide();
	              					swal("已新增購物車", "已修改", "success");
	              				},
	              				error : function() {
	              					console.log('omg');
	              					}
	              				});
	                		  },
	                	  error : function(){
	                		  alert('is your controller');
	                	 
	                	  }
	                  })
	            });
	        });
			$(".x").lightbox();
		});
</script>
<style type="text/css">
.startCss {
	text-align: center;
	vertical-align: middle;
/* 	white-space: nowrap; */
}
.table>tbody>tr>td,.table>tbody>tr>th{
	vertical-align: middle;
}
.container{
	min-height:760px;
}
.vilne{
white-space: nowrap;
vertical-align: middle;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="col-sm-12">
				<c:if test="${not empty trackList}">
				<h3><font color='#5A5AAD'><b>${memVO.mem_name}您好，您的追蹤書籍資料</b></font></h3>
				<table class="table" border='1' bordercolor='#CCCCFF' align="center" style="width:100%;">
				<tr>
							<th class="startCss">書籍圖片</th>
							<th class="startCss">書籍名稱</th>
							<th class="startCss">書籍作者</th>
							<th class="vilne">書籍價格</th>
							<th class="startCss">書籍描述</th>
							<th class="startCss">刪除追蹤</th>
							<th class="startCss">加入購物車</th>
							</tr>
							<c:forEach var="trackVO" items="${trackList}">
								<c:set var="bookVO" value="${bookService.getOneBook(trackVO.book_no)}" />
								<tr>
									<td class="startCss"><a href="<%=request.getContextPath()%>/Front-End/book/bookpage/${bookVO.book_no}"><img src="<%=request.getContextPath()%>/img?book_no=${bookVO.book_no}" style="width:auto;height: 135px;"></a></td>
									<td class="startCss"><b><a href="<%=request.getContextPath()%>/Front-End/book/bookpage/${bookVO.book_no}">${bookVO.book_name}</a></b></td>
									<td class="startCss"><b>${bookVO.book_author}</b></td>
									<td class="startCss"><b>${bookVO.book_price}元</b></td>
									<td class="startCss"><button class="text btn btn-danger" id="${bookVO.book_no}">內容點閱</button></td>
									<td class="startCss">
									<form action="<%=request.getContextPath()%>/Front-End/Track.do" method="post">
										<input type="hidden" name="book_no" value="${bookVO.book_no}" id="book_no">
										<input type="hidden" name="mem_no" value="${memVO.mem_no}" id="mem_no">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>" id="mem_no">
										<input type="hidden" name="action" value="delete_Track"> 
										<input type="submit" class="btn btn-danger test1" value="刪除書籍"></input>
							        </form>
							        </td>
							        <td class="startCss">
										<input type="hidden" name="item" value="${bookVO.book_no}:1">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="add"> 
										<input type="button" class="btn btn-danger addCar" value="加到購物車"></input>
							        </td>
								</tr>
							</c:forEach>
				</table>
				</c:if>
				</div>
			<c:if test="${empty trackList}">
				<div class="panel-warning">
					<div class="panel-heading">
						<div>
							<h3 class="panel-title">
						<font size="12">追蹤清單</font>
					</h3>
				   </div>
				  </div>
				</div>
				<div class="col-sm-12">
					<hr style="border: 0; border-top: 1px hidden #FF0000;">
				</div>
				<div class="h4">
				親愛的<font style="color:red">會員</font>您好,<br>
				<div class="col-sm-12">
					<hr style="border: 0; border-top: 1px hidden #FF0000;">
				</div>
				您目前無任何追蹤紀錄，<br>
				<div class="col-sm-12">
					<hr style="border: 0; border-top: 1px hidden #FF0000;">
				</div>
				提醒您，本頁面僅提供您查看追蹤清單。
				</div>
			</c:if>
			</div>
		</div>
<!-- 					<div style="position:fixed;bottom:0;left:0;right:0"> -->
		
<!-- 			</div> -->
	</div>
	<div>
				<jsp:include page="/Front-End/footer.jsp"></jsp:include>
	</div>

</body>
</html>



