<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%@ page import="com.mem.model.*"%>
<%@ page import="com.s_gro_info.model.*"%>
<%@ page import="com.s_gro_mem.model.*"%>
<%@ page import="com.s_book.model.*"%>


<%-- <% --%>
<!--  	S_gro_memVO s_gro_memVO = (S_gro_memVO) request.getAttribute("s_gro_memVO"); -->
<%-- %> --%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	MemService memService = new MemService();
	List<MemVO> list5 = memService.getAll();
	pageContext.setAttribute("list5", list5);
%>
<%
	Integer status = (Integer) session.getAttribute("status");
	Integer s_gro_no= (Integer) session.getAttribute("gro_id");
	Integer mem_no = (Integer) session.getAttribute("idd");
	System.out.println("status="+status);
	System.out.println("s_gro_no="+s_gro_no);
	System.out.println("mem_no="+mem_no);
		
	S_gro_infoService s_gro_infoService = new S_gro_infoService();
	S_gro_memService s_gro_memService = new S_gro_memService();


	List<S_gro_infoVO> list9 = s_gro_infoService.getAll();
	pageContext.setAttribute("list9", list9);
	S_gro_infoVO gro_ctr = s_gro_infoService.getByNo(s_gro_no);
// 		S_gro_infoVO gro_ctr = s_gro_infoService.getByNo(3);
	pageContext.setAttribute("gro_ctr", gro_ctr);
// 	System.out.println("gro_ctr="+gro_ctr);
		
	S_gro_memVO list1111 = s_gro_memService.findaguy(s_gro_no, mem_no);
// 		S_gro_memVO list1111 = s_gro_memService.findaguy(3, 103);
	pageContext.setAttribute("list1111", list1111);
// 		System.out.println("list1111="+list1111);
%>

<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" />	
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- BOOTSTRAP用 -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>


	
<title>讀書會</title>
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
<body id="body">
<jsp:include page="/Front-End/header.jsp"/>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">				
			<li>					
			<div>										
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
					<input type="hidden" name="action"	value="gro_manage"> 
					<button class="btn btn-default" type="submit" >									
						<font size="3px" id="font_menu_btn"><b>讀書會管理&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
					</button>
				</FORM>														
			</div>
			</li>														
		</ul>
	</div><!-- /.navbar-collapse -->
	
<c:if test="${status eq 1 }">

<!-- 	<font size="5px"><a href="#">我想申請讀書會活動</a></font><br> -->
	
	<c:forEach var="gro_all"  items="${list9}" varStatus="status">
		<c:set var="gro_id" value="${gro_id }"/>
			<c:if test="${gro_all.s_gro_no eq gro_id}">
<%-- 			<c:if test="${gro_all.s_gro_no eq 3}">	 --%>

<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-header">
					<font class="word">	讀書會名稱: <p class="pro_p">${gro_all.s_gro_name} </p> </font>
				</div>
				<div class="modal-body">
					<font class="word">		簡介:	<p class="pro_p">${gro_all.s_con}  </p> </font>
					<font class="word">	發起人會員: <p class="pro_p">
					<c:forEach var="memVO" items="${memSvc.all}">
	                    <c:if test="${gro_all.mem_no==memVO.mem_no}">     		
		                    	${memVO.mem_name}<br>	
	                    </c:if>
	                </c:forEach>
					</p> </font>
					<c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
	                    <c:if test="${gro_all.cs_no==s_gro_csVO.cs_no}">
		                 	<font class="word"> 類型: 【${s_gro_csVO.cs_name}】</font>
	                    </c:if>
	                </c:forEach><br>
	                <font class="word"> 讀書會狀態: 【${gro_all.s_gro_sta}】</font>
            	</div>
			</c:if>
	</c:forEach>

<%-- 	<c:if test="${gro_ctr.mem_no != idd }">	 --%>
<%-- 		<c:if test="${ empty list1111 }">	 --%>
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_mem/s_gro_mem.do"  > --%>
<!-- 			<BUTTON type=submit class="btn btn-success">加入讀書會</BUTTON>   -->
<%-- 			<input type="text" name="mem_no" size="45"  value="${idd}" /> --%>
<%-- 			<input type="text" name="s_gro_no" size="45"  value="${gro_id}" /> --%>
<!-- 			<input type="text" name="au_no" size="45"  value="2" /> -->
<%-- 			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<%-- 			<input type="text" name="requestURL" value="<%=request.getParameter("requestURL")%>"> --%>
<!-- 			<input type="hidden" name="action" value="ask_fr_in"> -->
<!-- 			</FORM> -->
<%-- 		</c:if>	 --%>
<%-- 	</c:if> --%>
			<c:if test="${ not empty list1111}">
				<c:if test="${club_sta eq 1 }">
					
			<br><br><p><button id="addMem" class="btn btn-primary btn-lg"  data-backdrop="false" data-toggle="modal" data-target="#myModal">更新讀書會資訊</button>
			<form  id="commentForm" METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do"  enctype="multipart/form-data">
				
				<div class="modal fade" id="myModal" tabindex="99" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">更新讀書會資訊</h4>
							</div>
							<div class="modal-body">
							
								<div>讀書會名稱<input type="text" name="s_gro_name" value="${gro_ctr.s_gro_name }"></div><br>
																																					
								<div style="clear:both">讀書會介紹<textarea cols="30" name="s_con" >${gro_ctr.s_con }</textarea></div><br>		
												
								<input type="hidden" name="mem_no" size="45"  value="${idd}"/>
								<input type="hidden" name="s_gro_no" size="45"  value="${gro_ctr.s_gro_no }" />
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">																
<%-- 								<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" /> --%>
								類型:<font color=red><b>*</b></font>
								<select size="1" name="cs_no">
									<c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
										<option value="${s_gro_csVO.cs_no}" ${(gro_ctr.cs_no==s_gro_csVO.cs_no)? 'selected':'' } >${s_gro_csVO.cs_name}
									</c:forEach>
								</select>
								<input type="hidden" name="s_gro_sta" size="45" value="正常"/>
							</div>
								<div class="modal-footer">
								
									<button type="button" id="add" class="btn btn-default" data-dismiss="modal">關閉</button>
								
									<button type="submit" class="btn btn-primary">確定更新</button> <!-- 註冊-->
									<input type="hidden"  name="action" value="updateclub"/>
								</div>
 						</div><!--/.modal-content -->
					</div><!--/.modal -->
				</div>				
			</form>				

				  				
				<br><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do" >
						<BUTTON type=submit  class="btn btn-danger">關閉讀書會</BUTTON>  
						<input type="hidden" name="mem_no" size="45"  value="${idd}" />
						<input type="hidden" name="s_gro_no" size="45"  value="${gro_id}" />
						<input type="hidden" name="s_gro_name" size="45"  value="${gro_ctr.s_gro_name}" />
						<input type="hidden" name="s_con" size="45"  value="${gro_ctr.s_con}" />
						<input type="hidden" name="cs_no" size="45"  value="${gro_ctr.cs_no}" />
						<input type="hidden" name="s_gro_sta" size="45"  value="已關閉" />
	<%-- 					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
						<input type="hidden" name="action" value="closedclub">
					</FORM>
				</c:if>
			</c:if>
			
			<hr/>
	            <div class="mt20 mb20 text-center">
	            	<a class="btn btn-primary twitter-login mr5" href="http://twitter.com/share?url=http://localhost:8081/AA103G40922/Front-End/s_gro/HomePageforClub.jsp這麼好的讀書會、不參加嗎?" target="_blank">twitter</a>
	            	<a class="btn btn-primary facebook-login" href="https://www.facebook.com/share.php?u=http://localhost:8081/AA103G40922/Front-End/s_gro/HomePageforClub.jsp" target="_blank">facebook</a>                               
	            </div>
                            
			</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
		</div>
	</div>
</c:if>		

	<c:if test="${status != 1 }">
		<c:forEach var="gro_all"  items="${list9}" varStatus="status">
			<c:set var="gro_id" value="${gro_id }"/>
				<c:if test="${gro_all.s_gro_no eq gro_id}">
					<font class="word"> 擁有讀書會狀態: 【${gro_all.s_gro_sta}】</font>
				</c:if>
		</c:forEach>
	</c:if>
	<div style="margin-top:80px">   </div>
	<jsp:include page="/Front-End/footer.jsp"/>
</body>

</html>