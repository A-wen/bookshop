<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>


<%@ page import="com.mem.model.*"%>
<%@ page import="com.s_gro_info.model.*"%>
<%@ page import="com.s_gro_mem.model.*"%>
<%@ page import="com.s_book.model.*"%>

<%
	MemService memService = new MemService();
	List<MemVO> list5 = memService.getAll();
	pageContext.setAttribute("list5", list5);
%>
<%
	S_gro_infoService s_gro_infoService = new S_gro_infoService();
	List<S_gro_infoVO> list9 = s_gro_infoService.getAll();
	pageContext.setAttribute("list9", list9);
	
	Integer s_gro_no= (Integer) session.getAttribute("gro_id");
	S_gro_infoVO gro_ctr = s_gro_infoService.getByNo(s_gro_no);
//	S_gro_infoVO gro_ctr = s_gro_infoService.getByNo(3);
	pageContext.setAttribute("gro_ctr", gro_ctr);
//  System.out.println("gro_ctr="+gro_ctr);
%>


<%	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
// 	Integer idd = memVO.getMemNo();	
// 	Integer idd = 101;
	Integer mem_no = (Integer) session.getAttribute("idd");
//	System.out.println("mem_no:"+mem_no);
// 	Integer status = (Integer) session.getAttribute("status");
// 	System.out.println("status:"+status);
	
	S_gro_memService s_gro_memService = new S_gro_memService();
	List<S_gro_memVO> list7 = s_gro_memService.findMemJoin(mem_no);
	
	if(list7.isEmpty()){
		pageContext.setAttribute("list7", 0);
	}else{
		pageContext.setAttribute("list7", list7);
	}
// 	System.out.println("list7:"+list7);
%>

<html >
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index2.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css"	>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
	<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css"	>

	<title>讀書會管理</title>
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
<jsp:include page="/Front-End/header.jsp"/>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
				<li class="active"><font size="3px" id="font_menu_btn"><b>會員:</b></font>
					<c:forEach var="mem_all" items="${list5}" varStatus="status">
						<c:if test="${mem_all.mem_no eq idd}">
<%-- 						<c:if test="${mem_all.memNo eq 101}"> --%>
<!-- 							<button class="btn btn-default"  id="btn_Member"> -->
							<font size="3px" id="font_menu_btn"><b>${mem_all.mem_name}&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
<!-- 							</button> -->
						</c:if>
					</c:forEach>
				</li>
<!-- 				<li>					 -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do"> --%>
<!-- 						<input type="hidden" name="action"	value="club">  -->
<!-- 						<button class="btn btn-default" type="submit" > -->
<!-- 						<font size="3px" id="font_menu_btn"><b>瀏覽讀書會&nbsp;&nbsp;&nbsp;&nbsp;</b></font> -->
<!-- 				        </button> -->
<!-- 					</FORM> -->

<!-- 				</li> -->
				<li>
					<div>					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_mem/s_gro_mem.do">
						<input type="hidden" name="action"	value="club_mem"> 
						<button type="submit" >
						<font size="3px" id="font_menu_btn"><b>瀏覽讀書會成員&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
				        </button>
					</FORM>
					</div>
				</li>
				<li>
					<div>					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_book/s_book.do">
						<input type="hidden" name="action"	value="club_book"> 
						<button type="submit" >
						<font size="3px" id="font_menu_btn"><b>瀏覽讀書會書單&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
				        </button>
					</FORM>
					</div>
				</li>
				
				<li>
					<button class="btn1" id="btn_Associations">
					<font size="3px" id="font_menu_btn"><b>讀書會&nbsp;&nbsp;&nbsp;&nbsp;</b></font></button>
					
			<div id="btn_Associations_list" style="display: none;">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
						<input type="hidden" name="action"	value="gro_manage"> 
						<button class="btn btn-default" type="submit" >
						<font size="3px" id="font_menu_btn"><b>讀書會管理&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
				        </button>
					</FORM>
			</div>
				</li>
				
				<li>
					<button id="myAssociations" class="btn2">
					<font size="3px" id="font_menu_btn"><b>已參加的讀書會&nbsp;&nbsp;&nbsp;&nbsp;</b></font></button>
					
				<div id="Associations_join" style="display: none;">
					<c:forEach var="s_gro_mem"  items="${list7}" varStatus="status" >

								<c:forEach var="s_gro_info"  items="${list9}" varStatus="status"  >
						
						<c:if test="${s_gro_mem.s_gro_no eq s_gro_info.s_gro_no}">
<%-- 						<c:if test="${3 eq s_gro_info.s_gro_no}">	 --%>
					
				
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
							<input type="hidden" name="action"	value="gro"> 
							<input type="hidden" name="s_gro_no" value="${s_gro_mem.s_gro_no}">
							<input type="hidden" name="s_gro_sta" value="${s_gro_info.s_gro_sta}">
							<button  type="submit"  class="btn btn-default">
								<font size="3px" id="font_menu_btn"><b>${s_gro_info.s_gro_name }&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
							</button>
						</FORM>
									
						</c:if>
								</c:forEach>
												
					</c:forEach>
			</div>
				</li>
				
				<li>
					<button  id="AssociationsCreate"  class="btn2" >
					<font size="3px" id="font_menu_btn"><b>我新增的讀書會&nbsp;&nbsp;&nbsp;&nbsp;</b></font></button>
					
			<div id="My_Associations" style="display: none;">
					<c:forEach var="s_gro_info"  items="${list9}" varStatus="status" >
						<c:if test="${s_gro_info.mem_no eq idd}">
<%-- 						<c:if test="${s_gro_info.mem_no eq 101}">	 --%>
							
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
								 <input type="hidden" name="action"	value="gro"> 
								 <input type="hidden" name="s_gro_no" value="${s_gro_info.s_gro_no}">
								 <input type="hidden" name="s_gro_sta" value="${s_gro_info.s_gro_sta}">
								<button  type="submit"  class="btn btn-default">
									<font size="3px" id="font_menu_btn"><b>${s_gro_info.s_gro_name }&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
								</button>
							</FORM>
							
						</c:if>
					</c:forEach>
			</div>
			</li>
		</ul>
	</div><!-- /.navbar-collapse -->


	<br><br><button id="addMem" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">建立讀書會</button>

	<form  id="commentForm" METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do"  enctype="multipart/form-data">
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">建立讀書會</h4>
					</div>
					<div class="modal-body">
					
						<div>讀書會名稱<input type="text" name="s_gro_name" /></div><br>
									
						
						  																					
						<div style="clear:both">讀書會介紹<textarea cols="30" name="s_con" ></textarea></div>		
						<input type="hidden" name="mem_no" size="45"  value="${idd}"/>
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 													
						
						
						<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" />
						<br><div>
							類型:<font color=red><b>*</b></font>
							<select size="1" name="cs_no">
									<c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
										<option value="${s_gro_csVO.cs_no}" ${(gro_ctr.cs_no==s_gro_csVO.cs_no)? 'selected':'' } >${s_gro_csVO.cs_name}
									</c:forEach>
							</select>
<%-- 								<a href="<%=request.getContextPath()%>/s_gro_cs/addS_gro_cs.jsp">新增類型</a> --%>							
						</div>	
						<div>
							<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
<!-- 							建立日期: -->
							<input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="hidden" name="cre_date" value="<%= date_SQL %>">
							<input type="hidden" name="s_gro_sta" size="45" value="正常" />
						</div>												
					</div>
					<div class="modal-footer">
						<button type="button" id="add" class="btn btn-default" data-dismiss="modal">關閉</button>
						
						<button type="submit" class="btn btn-primary">確定建立</button> <!-- 註冊-->
						<input type="hidden"  name="action" value="insert"/>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>				
	</form>
	
	<br><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do" >
		<b><font size="3px" color=orange>查詢讀書會成員:</font></b>
		<select size="1" name="s_gro_no">
			<c:forEach var="s_gro_infoVO" items="${list9}" > 
				<option value="${s_gro_infoVO.s_gro_no}">${s_gro_infoVO.s_gro_name}
			</c:forEach>   
		</select>
		<input type="submit" value="送出">
		<input type="hidden" name="action" value="listS_gro_mem_ByS_gro_no_A">
	</FORM>
		
<table>
	<tr>
	<c:if test="${status eq 1 }">
		<c:forEach var="s_gro_mem" items="${list7}" varStatus="status">
			<c:if test="${not empty s_gro_mem}">	
			<c:forEach var="gro_all" items="${list9}" varStatus="status">
								
				<c:if test="${s_gro_mem.s_gro_no eq  gro_all.s_gro_no }">
					
					<tr>
<%-- 						<c:if test="${s_gro_mem.mem_no ne  gro_all.mem_no}"> --%>

						
<!-- 			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
						<div class="modal-header">							
							<font size="5px"><b>讀書會名稱: ${gro_all.s_gro_name}&nbsp;&nbsp;&nbsp;&nbsp;</b></font><br>
						</div>
						<div class="modal-body">
							<font size="5px"><b>發起人會員:</b>
							<c:forEach var="memVO" items="${list5}">
			                    <c:if test="${gro_all.mem_no==memVO.mem_no}">     		
				                    	<b>${memVO.mem_name}&nbsp;&nbsp;&nbsp;&nbsp;</b>	
			                    </c:if>
			                </c:forEach>
							</font><br>
							
<%-- 							<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" />							 --%>
							<c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
			                    <c:if test="${gro_all.cs_no==s_gro_csVO.cs_no}">
				                 	<font size="5px"><b> 類型: 【${s_gro_csVO.cs_name}】&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
			                    </c:if>
			                </c:forEach>
			          	</div>
			          	<div class="modal-footer">
			      		
			              
					         <c:if test="${gro_all.mem_no eq idd }">					
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_mem/s_gro_mem.do">
										<input type="hidden" name="s_gro_no" value="${s_gro_mem.s_gro_no}" /><br>
										<font size="3px"><b>欲踢除成員名稱&nbsp;</b></font><br> 																 								
							            <select size="1" name="mem_no">
										<c:forEach var="memVO" items="${list5}">							
											<option value="${memVO.mem_no}" ${(s_gro_mem.mem_no == memVO.mem_no)? 'selected':'' } >${memVO.mem_name}
<%-- 											<option value="${s_gro_memVO.mem_no}" ${(s_gro_mem.mem_no == memVO.memNo)? 'selected':'' } >${memVO.mem_name}								  --%>
 										</c:forEach>
									</select> 
																		
										<input type="hidden" name="action" value="club_no"> 
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<button class="report btn btn-warning" type="submit" >確定</button>&nbsp;&nbsp;								
									</FORM>
							</c:if>												


					<c:if test="${gro_all.mem_no ne idd }">

<!-- 							<td>																			 -->
								<form id="forget" METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
									<input type="hidden"  name="s_gro_no" value="${gro_all.s_gro_no}"/>
									<input type="hidden" name="s_gro_name" size="45" value="${gro_all.s_gro_name}"/>
									<input type="hidden" name="mem_no" size="45" value="${gro_all.mem_no}"/>
									<input type="hidden"  name="cs_no" value="${gro_all.cs_no}"/>																																																								
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<input type="hidden"  name="action" value="club_report"/>
									<button type="submit" class="btn btn-danger">檢舉讀書會</button> <!-- 寄出讀書會資訊到信箱-->																			
								</form>	
								<c:if test="${not empty club_report1}">
									<script>
		// 								$(document).ready(function() {
											swal(
												 '您已檢舉成功',
												 '',
												 'success'
												)
		// 								});
									</script>
								</c:if>					
<!-- 							</td> -->
<!-- 							<td>																			 -->
								<form id="forget" METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_mem/s_gro_mem.do">
									<input type="hidden"  name="s_gro_no" value="${gro_all.s_gro_no}"/>
	<%-- 								<input type="hidden" name="mem_no" size="45" value="${gro_all.mem_no}"/> --%>
									<select size="1" name="mem_no">
									<c:forEach var="memVO" items="${list5}">							
										<option value="${memVO.mem_no}" ${(gro_all.mem_no == memVO.mem_no)? 'selected':'' } >${memVO.mem_name}
	<%-- 									<option value="${s_gro_memVO.mem_no}" ${(s_gro_mem.mem_no == memVO.memNo)? 'selected':'' } >${memVO.mem_name} --%>								
									</c:forEach>
									</select>																																																							
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<input type="hidden"  name="action" value="mem_report"/>
									<button type="submit" class="btn btn-warning">檢舉讀書會成員</button> <!-- 寄出讀書會資訊到信箱-->																			
								</form>	
								<c:if test="${not empty mem_report1}">
									<script>
		// 								$(document).ready(function() {
											swal(
												 '檢舉成功',
												 '',
												 'success'
												)
		// 								});
									</script>
								</c:if>					
<!-- 							</td> -->

							</c:if>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
<!-- 	</div> -->
<%-- 						</c:if> --%>
					</tr>
																													
				</c:if>
				
			</c:forEach>
		</c:if>
		</c:forEach>
</c:if>
		
		<c:if test="${status ne 1 }">
			<c:forEach var="gro_all"  items="${list9}" varStatus="status">
				<c:set var="gro_id" value="${gro_id }"/>
				<c:if test="${gro_all.s_gro_no eq gro_id}">
					<font class="word"> 擁有讀書會: 【${gro_all.s_gro_name}】-【${gro_all.s_gro_sta}】</font>
				</c:if>
			</c:forEach>
		</c:if>
	</tr>
<div style="margin-top:100px"></div>
<jsp:include page="/Front-End/footer.jsp"/>
</table>

</body>
</html>					