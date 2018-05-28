<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.mem.model.*"%>
<%@ page import="com.s_gro_info.model.*"%>
<%@ page import="com.s_gro_mem.model.*"%>
<%@ page import="com.s_book.model.*"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer idd = (Integer) session.getAttribute("idd");
	System.out.println("idd="+idd);
	Integer club_sta = (Integer) session.getAttribute("club_sta");
	System.out.println("club_sta="+club_sta);
	MemService memService = new MemService();
	List<MemVO> list5 = memService.getAll();
	pageContext.setAttribute("list5", list5);
%>

<%
	S_gro_infoService s_gro_infoService = new S_gro_infoService();
	List<S_gro_infoVO> list9 = s_gro_infoService.getAll();
	pageContext.setAttribute("list9", list9);
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
	System.out.println("list7:"+list7);
	session.setAttribute("clubmem", clubmem);
	System.out.println("clubmem:"+clubmem);
%>

<jsp:useBean id="listS_gro_infos_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" />
<jsp:useBean id="s_bookSvc" scope="page" class="com.s_book.model.S_bookService" />
<jsp:useBean id="s_gro_disSvc" scope="page" class="com.s_gro_dis.model.S_gro_disService" />

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index2.js"></script>	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Front-End/css/sweetalert.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Front-End/css/lobibox.css">	
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
	<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
	<link href="<%=request.getContextPath()%>/css/colony.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/all.css" rel="stylesheet">
	
<title>讀書會查詢</title>
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
<%-- 			<c:if test="${ not empty list7 }"> --%>
			<c:if test="${ clubmem ne 0 }">
				<li><button class="btn1" id="btn_Associations">
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

						<c:forEach var="s_gro_info"  items="${list9}" varStatus="status" >
						<c:if test="${s_gro_mem.s_gro_no eq s_gro_info.s_gro_no}">
<%-- 						<c:if test="${3 eq s_gro_info.s_gro_no}">	 --%>
					
				
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
										 <input type="hidden" name="action"	value="gro"> 
										 <input type="hidden" name="s_gro_no" value="${s_gro_mem.s_gro_no}">
										 <input type="hidden" name="s_gro_sta" value="${s_gro_info.s_gro_sta}">
<%-- 										 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> --%>
										 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
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
							 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">							 
								<button  type="submit"  class="btn btn-default">
									<font id="font_menu_btn">${s_gro_info.s_gro_name }</font>
								</button>
							</FORM>
							
						</c:if>
					</c:forEach>
			</div>
		</li>			
		</c:if>	
	</ul>

<br><hr>
<section id="body-section">
        <div id="main" class="body-height-min">
            <div class="container">                          
	           	<div id="contentsList">
	                <c:forEach var="s_gro_infoVO" items="${listS_gro_infos_ByCompositeQuery}">
	                    <div class="contents-d contents-m contents">
	                        <div class="contents-child">
                                <span class="dateView">${s_gro_infoVO.cre_date}</span>
                                <span class="row cover" style="background-image:url('images/${s_gro_infoVO.s_gro_no}.jpg');"></span>
                                <span class="titleArea">
									<h4>${s_gro_infoVO.s_gro_name}</h4>
                                </span>                                                             
                                <span class="row contents-child-header">
									<span class="left"><h3>${s_gro_infoVO.s_con}</h3></span>
                                </span>
                                <span>
                                	<c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
										<c:if test="${s_gro_infoVO.cs_no==s_gro_csVO.cs_no}">
											<font size="3px" id="font_menu_btn"><b>【${s_gro_csVO.cs_name}】&nbsp;&nbsp;</b></font>
										</c:if>
									</c:forEach>
 			   						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_book/s_book.do" >
								   	 <font size="3px" id="font_menu_btn"><b>查詢書單:</b></font>
								   	 <button type="submit" class="btn btn-primary">按我查詢</button>
								     <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
								     <input type="hidden" name="action" value="listS_books_ByCompositeQuery">
								   </FORM> 									
								</span>
	                        </div>
	                    </div>
	         		</c:forEach>                                                                                          
	            </div>
	        </div>
        </div>
    </section>
    <div style="margin-top:80px"></div>
<jsp:include page="/Front-End/footer.jsp"/>
</body>

</html>