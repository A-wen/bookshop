<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%@ page import="com.s_gro_au.model.*"%>


<%
	S_gro_auService s_gro_auSvc = new S_gro_auService();
    List<S_gro_auVO> list = s_gro_auSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>讀書會權限</title>

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
	<section id="body-section">
        <div id="main" class="body-height-min">
            <div id="profile-image-d">
                <div id="profile-image-d-contents" style="background-color: #e6e7e8">
                </div>
            </div>
		<div class="container">
			<c:forEach var="S_gro_auVO" items="${list}">
	    	<div class="inner-body-frame col-md-8">   	
	        	<div id="inner-body" class="inner-body no-through">
	            	<div class="inner-body-contents">                                                     
	                	<div id="newsList">
							<div class="entry" style="border-color: #15967d;">
                            	<div class="entry-main">
                                		<div class="entry-header">
	                                    	<div class="user-text">
	                                    		<font size="5px" id="font_menu_btn"><b>權限層級&nbsp;:&nbsp;${S_gro_auVO.au_lev}</b></font><br>	                                                        		                                                                                
	                                        </div>                                         
                                        </div>
                                        <div class="entry-body">
                                        	<font size="5px" id="font_menu_btn"><b>權限簡介&nbsp;:&nbsp;${S_gro_auVO.au_state}</b></font>                                      							                                                      
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
</html>