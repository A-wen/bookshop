<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
    
<%@ page import="com.mem.model.*"%>
<%@ page import="com.s_gro_info.model.*"%>
<%@ page import="com.s_gro_mem.model.*"%>
<%@ page import="com.s_book.model.*"%>   
 
 
<jsp:useBean id="listS_gro_mem_ByS_gro_no" scope="request" type="java.util.Set" />
<jsp:useBean id="s_gro_infoSvc" scope="page" class="com.s_gro_info.model.S_gro_infoService" />
<jsp:useBean id="s_gro_auSvc" scope="page" class="com.s_gro_au.model.S_gro_auService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>讀書會成員</title>

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
	<script src="<%=request.getContextPath()%>/js/index2.js"></script>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link href="<%=request.getContextPath()%>/css/colony.css" rel="stylesheet">
	
	
</head>
<body>	
<jsp:include page="/Front-End/header.jsp"/>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">				
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
		</c:if>	
	</ul><br><br>
</div>				
	<section id="body-section">
        <div id="main" class="body-height-min">
            <div id="profile-image-d">
                <div id="profile-image-d-contents" style="background-color: #e6e7e8">
                </div>
            </div>
		<div class="container">
			<c:forEach var="s_gro_memVO" items="${listS_gro_mem_ByS_gro_no}">
	    	<div class="inner-body-frame col-md-8">   	
	        	<div id="inner-body" class="inner-body no-through">
	            	<div class="inner-body-contents">                                                     
	                	<div id="newsList">
							<div class="entry" style="border-color: #15967d;">
                            	<div class="entry-main">
                                		<div class="entry-header">
	                                    	<div class="user-text">
	                                    		<font size="5px" id="font_menu_btn"><b>讀書會名稱:</b></font>
                                         		<c:forEach var="s_gro_infoVO" items="${s_gro_infoSvc.all}">
								                    <c:if test="${s_gro_memVO.s_gro_no==s_gro_infoVO.s_gro_no}">     		
									                    	<font size="5px" id="font_menu_btn"><b>${s_gro_infoVO.s_gro_name}</b></font><br>
								                    </c:if>
								                </c:forEach>                                                                                
	                                        </div>                                         
                                        </div>
                                        <div class="entry-body">
                                        	<font size="5px" id="font_menu_btn"><b>成員名稱:</b></font>
                                        	<c:forEach var="memVO" items="${memSvc.all}">
							                    <c:if test="${s_gro_memVO.mem_no==memVO.mem_no}">     		
								                    	<font size="5px" id="font_menu_btn"><b>${memVO.mem_name}</b></font><br>	
							                    </c:if>
							                </c:forEach>
							                <font size="5px" id="font_menu_btn"><b>權限:</b></font>
							                <c:forEach var="s_gro_auVO" items="${s_gro_auSvc.all}">
							                    <c:if test="${s_gro_memVO.au_no==s_gro_auVO.au_no}">
								                    	<font size="5px" id="font_menu_btn"><b>【${s_gro_auVO.au_lev}】</b></font>
							                    </c:if>
							                </c:forEach>                                      
                                        </div>
                                        
                                        <div class="entry-footer">                                      
                                            <div class="post-btn-area">                                                                      
                                                <span class="postButton">                                                
<!-- 	                                                <button type="button" class="btn btn-primary activate commentPostAction"> -->
<!-- 	                                                <i class="fa fa-comment">權限簡介</i>	                                                 -->
<!-- 	                                                </button>                                                 -->
                                                		<a href="<%=request.getContextPath()%>/Front-End/s_gro/listAllS_gro_au.jsp">權限簡介</a>
                                                </span>
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

</body>
<jsp:include page="/Front-End/footer.jsp"/>
</html>