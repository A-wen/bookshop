<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.s_gro_info.model.*"%>

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
	S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
    List<S_gro_infoVO> list = s_gro_infoSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Book41 後台讀書會管理</title>
	<style type="text/css">
			.list-group-item{
				border-color: #f00;
			}
			.list-group-item:first-child{
				border-radius: 0;
			}
			.list-group-item:last-child{
				border-radius: 0;
			}
			.pagination-info{
				margin: 20px 0;
				padding: 10px 0;
				font-size: 16px;
			}
			.table-striped > tbody > tr:nth-of-type(odd){
				background-color: #ffa;
			}
			.table-hover > tbody > tr:hover{
				background-color: #fa0;
			}
			.breadcrumb{
				background-color: transparent;
			}
			.breadcrumb li.active{ color: #f00; }
			.breadcrumb li a{
				color: #fa0;
			}
		</style>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backhome.css" />
</head>
<body>
	<!-- Header的位置-->
	<%@ include file="/Back-End/back-header.jsp"%>
	<!-- Header的位置-->
	<div class="container-fluid" id="main">
		<!--/col-->
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
			<div class="col-xs-4 col-sm-4 col-md-3 col-lg-2" id="sidebar" role="navigation">
				<!-- Sidebar的位置-->
				<%@ include file="/Back-End/back-sidebar.jsp"%>
				<!-- Sidebar的位置-->
			</div>

			<div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">
				<!-- 內頁的位置-->
				<table class="table table-hover table-striped table-bordered table-condensed">
					<tr>
						<th><font size="3px" id="font_menu_btn"><b>讀書會編號&nbsp;&nbsp;</b></th>
						<th><font size="3px" id="font_menu_btn"><b>讀書會名稱&nbsp;&nbsp;</b></th>
<!-- 						<th><font size="3px" id="font_menu_btn"><b>讀書會內容介紹&nbsp;&nbsp;</b></th> -->
						<th><font size="3px" id="font_menu_btn"><b>發起人會員編號&nbsp;&nbsp;</b></th>
						<th><font size="3px" id="font_menu_btn"><b>類別編號&nbsp;&nbsp;</b></th>
<!-- 						<th><font size="3px" id="font_menu_btn"><b>建立時間&nbsp;&nbsp;</b></th> -->
<!-- 						<th>查詢書單</th> -->
<!-- 						<th>查詢討論文章</th> -->
						<th><font size="3px" id="font_menu_btn"><b>讀書會狀態&nbsp;&nbsp;</b></th>
						<th><font size="3px" id="font_menu_btn"><b>凍結讀書會&nbsp;&nbsp;</b></th>
						<th><font size="3px" id="font_menu_btn"><b>取消凍結讀書會&nbsp;&nbsp;</b></th>
					</tr>
<%-- 					<%@ include file="page1.file" %>  --%>
					<c:forEach var="s_gro_infoVO" items="${list}">
						<tr align='center' valign='middle' ${(s_gro_infoVO.s_gro_no==param.s_gro_no) ? 'bgcolor=orange':''}>
							<td><font size="3px" id="font_menu_btn"><b>${s_gro_infoVO.s_gro_no}&nbsp;&nbsp;</b></font></td>
							<td><font size="3px" id="font_menu_btn"><b>${s_gro_infoVO.s_gro_name}&nbsp;&nbsp;</b></font></td>
<%-- 							<td><font size="3px" id="font_menu_btn"><b>${s_gro_infoVO.s_con}&nbsp;&nbsp;</b></font></td> --%>
							<td><c:forEach var="memVO" items="${memSvc.all}">
				                    <c:if test="${s_gro_infoVO.mem_no==memVO.mem_no}">     		
					                    <font size="3px" id="font_menu_btn"><b>${memVO.mem_name}&nbsp;&nbsp;</b></font><br>	
				                    </c:if>
				                </c:forEach>
							</td>
							<td><c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
				                    <c:if test="${s_gro_infoVO.cs_no==s_gro_csVO.cs_no}">
					                    <font size="3px" id="font_menu_btn"><b>【${s_gro_csVO.cs_name}】&nbsp;&nbsp;</b></font>
				                    </c:if>
				                </c:forEach>
							</td>
<%-- 							<td><font size="3px" id="font_menu_btn"><b>${s_gro_infoVO.cre_date}&nbsp;&nbsp;</b></font></td>						 --%>
							<td><font size="3px" id="font_menu_btn"><b>${s_gro_infoVO.s_gro_sta}&nbsp;&nbsp;</b></font></td>
							<td>
							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
							     <BUTTON type=submit class="btn btn-warning"><font size="3px" id="font_menu_btn"><b>確定&nbsp;&nbsp;</b></BUTTON>
							     <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
							     <input type="hidden" name="s_gro_name" value="${s_gro_infoVO.s_gro_name}">
							     <input type="hidden" name="s_con" value="${s_gro_infoVO.s_con}">
							     <input type="hidden" name="mem_no" value="${s_gro_infoVO.mem_no}">
							     <input type="hidden" name="cs_no" value="${s_gro_infoVO.cs_no}">
							     <input type="hidden" name="cre_date" value="${s_gro_infoVO.cre_date}">
							     <input type="hidden" name="s_gro_sta" value="已凍結">
							     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<%-- 							     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
							     <input type="hidden" name="action"	value="update"></FORM>
							</td>
							<td>
							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
							     <BUTTON type=submit class="btn btn-warning"><font size="3px" id="font_menu_btn"><b>確定&nbsp;&nbsp;</b></BUTTON>
							     <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
							     <input type="hidden" name="s_gro_name" value="${s_gro_infoVO.s_gro_name}">
							     <input type="hidden" name="s_con" value="${s_gro_infoVO.s_con}">
							     <input type="hidden" name="mem_no" value="${s_gro_infoVO.mem_no}">
							     <input type="hidden" name="cs_no" value="${s_gro_infoVO.cs_no}">
							     <input type="hidden" name="cre_date" value="${s_gro_infoVO.cre_date}">
							     <input type="hidden" name="s_gro_sta" value="正常">
							     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<%-- 							     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
							     <input type="hidden" name="action"	value="update"></FORM>
							</td>							
						</tr>
					</c:forEach>
				</table>
<%-- 				<%@ include file="page2.file" %> --%>
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