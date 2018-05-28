<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%-- <jsp:include page="/Front-End/header.jsp"/> --%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.s_gro_info.model.*"%>
<%@ page import="com.s_gro_mem.model.*"%>
<%@ page import="com.s_book.model.*"%>
<jsp:useBean id="memService" scope="session" class="com.mem.model.MemService"/>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
// 	Integer idd = memVO.getMemNo();
	Integer idd = 110;
	session.setAttribute("idd", idd);
	//	System.out.println("idd:"+idd);

// 	MemService memService = new MemService();
	List<MemVO> list5 = memService.getAll();
	pageContext.setAttribute("list5", list5);
%>

<%
	S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
    List<S_gro_infoVO> list = s_gro_infoSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%
	
	Integer clubmem = 0;
	
	S_gro_memService s_gro_memService = new S_gro_memService();
	List<S_gro_memVO> list7 = s_gro_memService.findMemJoin(idd);
// 	System.out.println(idd);

	if(list7.isEmpty()){
		pageContext.setAttribute("list7", 0);
	}else{
		pageContext.setAttribute("list7", list7);
		clubmem = 1;
	}
	System.out.println("list7:"+list7.size());
	session.setAttribute("clubmem", clubmem);
	System.out.println("clubmem:"+clubmem);
%>

<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html >
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<title>讀書會資訊 </title>
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
</head>
<body>
<jsp:include page="/Front-End/header.jsp" />

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
				<li class="active"><a href="<%=request.getContextPath()%>/s_gro_dis/listAllS_gro_dis.jsp">瀏覽讀書會相關討論文章</a></li>
				<li><a href="<%=request.getContextPath()%>/s_gro_cs/listAllS_gro_cs.jsp">瀏覽讀書會類型</a></li>
				<li><a href="<%=request.getContextPath()%>/s_gro_au/listAllS_gro_au.jsp">瀏覽讀書會權限</a></li>
		</ul>
		
		<ul class="nav navbar-nav navbar-right">
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">新增表單<b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="<%=request.getContextPath()%>/Front-End/Cre_s_gro_page.jsp">新增讀書會</a></li>
				<li><a href="#">新增讀書會討論文章</a></li>
				<li><a href="#">新增讀書會書單</a></li>
			</ul>
		</li>
	</ul>
	</div><!-- /.navbar-collapse -->

	<div class="navbar-form navbar-left" role="search">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do" >
				<div class="form-group">
					<input type="text" name="s_gro_name" class="form-control" placeholder="請輸入搜尋關鍵字">
					<input type="hidden" name="action" value="listS_gro_infos_ByCompositeQuery">
				</div>
				<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-zoom-in"></i>搜尋</button>
		</FORM>	
	</div>
	
	<br><br><br><br>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_cs/s_gro_cs.do" >
		<b><font color=orange>選擇類型:</font></b>
		<select size="1" name="cs_no">
			<c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}" > 
				<option value="${s_gro_csVO.cs_no}">${s_gro_csVO.cs_name}
			</c:forEach>   
		</select>
		<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-zoom-in"></i>搜尋</button>
		<input type="hidden" name="action" value="listS_gro_infos_ByCs_no_A">
	</FORM>					  
	
<table class="table table-hover table-striped table-bordered table-condensed">
	<thead>
			<tr>
				<th>讀書會編號</th>
				<th>讀書會名稱</th>
				<th>讀書會內容介紹</th>
				<th>發起人會員編號</th>
				<th>類別編號</th>
				<th>建立時間</th>
				<th>查詢書單</th>
				<th>查詢討論文章</th>
				<th>讀書會狀態</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody>
	<c:forEach var="s_gro_infoVO" items="${list}">
		<tr align='center' valign='middle' ${(s_gro_infoVO.s_gro_no==param.s_gro_no) ? 'bgcolor=orange':''}>	
			<td>${s_gro_infoVO.s_gro_no}</td>
			<td>${s_gro_infoVO.s_gro_name}</td>
			<td>${s_gro_infoVO.s_con}</td>
			<td><c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${s_gro_infoVO.mem_no==memVO.memNo}">     		
	                    	${memVO.memName}<br>	
                    </c:if>
                </c:forEach>
			</td>
			<td><c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
                    <c:if test="${s_gro_infoVO.cs_no==s_gro_csVO.cs_no}">
	                    		【${s_gro_csVO.cs_name}】
                    </c:if>
                </c:forEach>
			</td>
			<td>${s_gro_infoVO.cre_date}</td>
			<td>
			   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_book/s_book.do" >
			   	 <button type="submit" class="btn btn-default">按我查詢</button>
			     <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			     <input type="hidden" name="action" value="listS_books_ByCompositeQuery">
			   </FORM>
			</td>
			<td>
			   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_dis/s_gro_dis.do" >
			     <button type="submit" class="btn btn-default">按我查詢</button>
			     <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			     <input type="hidden" name="action" value="listS_gro_diss_ByCompositeQuery">
			   </FORM>
			</td>
			<td>${s_gro_infoVO.s_gro_sta}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
			     <button type="submit" class="btn btn-default">修改</button>
			     <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
			    <button type="submit" class="btn btn-default">刪除</button>
			    <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<!-- jQuery -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 		<script src="Hello World"></script>
 		
<jsp:include page="/Front-End/footer.jsp" />
</body>
</html>
