<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.mem.model.*"%>
<%@ page import="com.s_gro_info.model.*"%>
<%@ page import="com.s_gro_mem.model.*"%>
<%@ page import="com.s_book.model.*"%>


<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer mem_no = (Integer) session.getAttribute("idd");
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
	List<S_gro_memVO> list7 = s_gro_memService.findMemJoin(mem_no);
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

<jsp:useBean id="listS_books_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="s_gro_infoSvc" scope="page" class="com.s_gro_info.model.S_gro_infoService" />

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index2.js"></script>
	<link href="<%=request.getContextPath()%>/css/all.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link href="<%=request.getContextPath()%>/css/colony.css" rel="stylesheet">
    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<title>Book思議 | 查詢讀書會書單</title>
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
	
<section id="body-section">
        <div id="main" class="body-height-min">
            <div id="profile-image-d">
                <div id="profile-image-d-contents" style="background-color: #e6e7e8">
                </div>
            </div>
		<div class="container">
			<c:forEach var="s_bookVO" items="${listS_books_ByCompositeQuery}">
	    	<div class="inner-body-frame col-md-8">   	
	        	<div id="inner-body" class="inner-body no-through">
	            	<div class="inner-body-contents">                                                     
	                	<div id="newsList">
							<div class="entry" style="border-color: #15967d;">
                            	<div class="entry-main">
                                		<div class="entry-header">
	                                    	<div class="user-text">
	                                    		<font size="5px" id="font_menu_btn"><b>讀書會編號:</b></font>
                                         		<c:forEach var="s_gro_infoVO" items="${s_gro_infoSvc.all}">
								                    <c:if test="${s_bookVO.s_gro_no==s_gro_infoVO.s_gro_no}">     		
									                    	${s_gro_infoVO.s_gro_name}<br>
								                    </c:if>
								                </c:forEach>                                                                                
	                                        </div>                                         
                                        </div>
                                        <div class="entry-body">
                                        	<font size="5px" id="font_menu_btn"><b>建立日期:${s_bookVO.cre_date}</b></font>
                                        	
							                <font size="5px" id="font_menu_btn"><b>結束日期:${s_bookVO.end_date}</b></font>
							                                                      
                                        </div>
                                        <div class="entry-footer">                                      
                                            <div class="post-btn-area">                                                                                                                      
	                                               <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_book_det/s_book_det.do" >
												   		<button class="btn btn-primary" type="submit" >
												   		<input type="hidden" name="s_book_no" value="${s_bookVO.s_book_no}">
												    	<input type="hidden" name="action" value="listS_book_dets_ByCompositeQuery">
												     	<font size="3px" id="font_menu_btn"><b>按我查詢&nbsp;&nbsp;&nbsp;&nbsp;</b></font></button>
												   </FORM> 	                                                                                                
                                            </div>                                         
                                        </div>
                                    </div>                                                                                                                                  
                                </div>
							</div>                            
                        </div>                       
                    </div>                                    
                </div> 
             </c:forEach>               
            </div>          
        </div>
    </section>
    <div style="margin-top:80px"></div>
    <jsp:include page="/Front-End/footer.jsp"/>
</body>

</html>