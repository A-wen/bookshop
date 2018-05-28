<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.order_info_view.model.Order_Info_ViewVO"%>
<%@page import="com.order_info_view.model.Order_Info_ViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date current = new Date();
	String nowtime = sdFormat.format(current);
	pageContext.setAttribute("Date", nowtime);
%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />	
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book思議 | 個人交易紀錄</title>

<jsp:useBean id="order_InfoService" scope="page" class="com.order_info.model.Order_InfoService" />
<jsp:useBean id="order_Info_ViewService" scope="page" class="com.order_info_view.model.Order_Info_ViewService" />
<jsp:useBean id="memService" scope="session" class="com.mem.model.MemService" />
<jsp:useBean id="bookService" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="order_ItemService" scope="page" class="com.order_item.model.Order_ItemService" />
<style type="text/css">
.startCss {
	text-align: center;
	vertical-align: middle;
	white-space: nowrap;
}
.container {
	min-height: 800px;
}
</style>
</head>
<body>
<jsp:include page="/Front-End/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<c:if test="${not empty order_InfoService.getByMemNo(memVO.getMem_no())}">
					<h3><font color='#5A5AAD'><b>${memVO.mem_name}您好，您的交易紀錄如下：</b></font></h3>
					
					<table class="table table-bordered" align="center" style="margin-top:30px">
						<tr>
							<th class="startCss"><b>訂單編號</b></th>
							<th class="startCss" width=150><b>訂單價格</b></th>
							<th class="startCss" width=550><b>訂單成立時間</b></th>
							<th class="startCss" width=550><b>付款方式</b></th>
							<th class="startCss" width=1800><b>寄送地址</b></th>
							<th class="startCss"><b>聯絡電話</b></th>
							<th class="startCss" width=1400><b>處理狀態</b></th>
						</tr>
						<c:forEach var="order_InfoVO" items="${order_InfoService.getByMemNo(memVO.getMem_no())}">
							<tr>
								<td class="startCss"><b>${order_InfoVO.o_Id}</b></td>
								<td class="startCss" width=450><b>${order_InfoVO.o_Sum}元</b></td>
								<td class="startCss" width=450><b>${order_InfoVO.o_Date}</b></td>
								<td class="startCss" width=450><b>${order_InfoVO.orderStatus.o_Desc}</b></td>
								<td class="startCss" width=1200><b>${order_InfoVO.addr}</b></td>
								<td class="startCss"><b>${order_InfoVO.tel}</b></td>
								<td class="startCss" width=450><b>${order_InfoVO.deliveryStatus.d_Desc}</b></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>

				<c:if test="${empty order_InfoService.getByMemNo(memVO.getMem_no())}">
					<div class="panel-warning">
						<div class="panel-heading">
							<div>
								<h3 class="panel-title">
									<font size="12">交易清單</font>
								</h3>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<hr style="border: 0; border-top: 1px hidden #FF0000;">
					</div>
					<div class="h4">
						親愛的<font style="color: red">會員</font>您好，<br>
						<div class="col-sm-12">
							<hr style="border: 0; border-top: 1px hidden #FF0000;">
						</div>
						您目前無任何交易紀錄，<br>
						<div class="col-sm-12">
							<hr style="border: 0; border-top: 1px hidden #FF0000;">
						</div>
						提醒您，本頁面僅提供您查看交易清單。<br>
						<div class="col-sm-12">
							<hr style="border: 0; border-top: 1px hidden #FF0000;">
						</div>
						現在時間為:<font style="color: red">${Date}</font>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<jsp:include page="/Front-End/footer.jsp"></jsp:include>

</body>
</html>