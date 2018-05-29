<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.cm.model.*"%>
<%@ page import="java.io.*"%>

<%
    CmService cmSvc = new CmService();
	List<CmVO> list = cmSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="CmService" scope="page" class="com.cm.model.CmService" />
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/book41.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/landing-page.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/font-awesome.css"	rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/all.css"	rel="stylesheet">
	<!-- 以下新的 -->
	<link href="<%=request.getContextPath()%>/ncss/style.css" rel="stylesheet" type="text/css" media="all" />	
	<!-- start menu -->
	<link href="<%=request.getContextPath()%>/ncss/memenu.css" rel="stylesheet" type="text/css" media="all" />
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/njs/responsiveslides.min.js"></script>
<!-- 以上新的 -->
<title>Book思議 | 首頁</title>
	
</head>
<body>
 	<jsp:include page="header.jsp"/>
	
    <!-- /.intro-header -->
     <a name="about"></a>
    <div class="intro-header">
     <h1>BOOK41</h1>
    <h3>Learning</h3>                              
        <div class="container">
            <div class="row">
                <div class="col-lg-12">    
                </div>
            </div>
        </div>       
    </div>

    <!-- Page Content -->
    <div class="container">
    <div id="carousel-id" class="carousel slide" data-ride="carousel">
            <!-- 幻燈片小圓點區 -->
<!--             定位點需要再修改，啟動時位置有問題 -->
            <ol class="carousel-indicators">
<!--             	<li data-target="#carousel-id" data-slide-to="0" class="active"></li> -->
            	<c:forEach var="cmVO" begin="1" items="${list}" varStatus="i">   
                <li data-target="#carousel-id" data-slide-to="${i.count}" class=""></li>
                </c:forEach> 
            </ol>
            <!-- 幻燈片主圖區 -->
            <div class="carousel-inner" >
                <c:forEach var="cmVO" items="${list}">                
                <div class="item ">
                	<a href="${cmVO.cmUrl}">       
                    <img src="<%=request.getContextPath()%>/cm/CMReader.do?cm_no=${cmVO.cmNo}" style="height:400px; " alt="廣告">
                    </a>
                    <div class="container">
                        <div class="carousel-caption">                   
                        </div>
                    </div>
                </div>
                </c:forEach>    
            
                 <div class="item active">
                    <img src="<%=request.getContextPath()%>/cm/CMReader.do?cm_no=3001" alt="廣告" style="height:400px;" >
                    <div class="container">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>
               
            </div>

            <!-- 上下頁控制區 -->
            <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
            <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
        </div>
      	</div>
      	
      	<!--新的頁面-->      	
      	
   <div class="content">
	<div class="container">
	<div class="content-top">
		<!-- -->
		<!-- -->
		<div class="grid-in">
			<div class="col-md-4 grid-top">
				<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=100" class="b-link-stripe b-animate-go  thickbox">
					
					<img class="img-responsive" src="<%=request.getContextPath()%>/images/pi.jpg" alt="" style="height:340px">
							
							<div class="b-wrapper">
									<h3 class="b-animate b-from-left    b-delay03 ">
										<span>電腦資訊</span>	
									</h3>
								</div>
				</a>
		

			<p><a href="single.html">Information Engineering</a></p>
			</div>
			<div class="col-md-4 grid-top">
				<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=400" class="b-link-stripe b-animate-go  thickbox">
				<img class="img-responsive" src="<%=request.getContextPath()%>/images/pi1.jpg" alt="" style="height:340px">
					<div class="b-wrapper">
									<h3 class="b-animate b-from-left    b-delay03 ">
										<span>文學小說</span>	
									</h3>
								</div>
				</a>
			<p><a href="single.html">Fiction</a></p>
			</div>
			<div class="col-md-4 grid-top">
				<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=700" class="b-link-stripe b-animate-go  thickbox">
					<img class="img-responsive" src="<%=request.getContextPath()%>/images/pi2.jpg" alt="" style="height:340px">
					<div class="b-wrapper">
									<h3 class="b-animate b-from-left    b-delay03 ">
										<span>商業理財</span>	
									</h3>
								</div>
				</a>
			<p><a href="single.html">Finance and economics</a></p>
			</div>
					<div class="clearfix"> </div>
		</div>
		<div class="grid-in">
			<div class="col-md-4 grid-top">
				<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=600" class="b-link-stripe b-animate-go  thickbox">
				<img class="img-responsive" src="<%=request.getContextPath()%>/images/pi3.jpg" alt="">
					<div class="b-wrapper">
									<h3 class="b-animate b-from-left    b-delay03 ">
										<span>生活風格</span>	
									</h3>
								</div>
				</a>
			<p><a href="single.html">Life</a></p>
			</div>
			<div class="col-md-4 grid-top">
				<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=500" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="<%=request.getContextPath()%>/images/pi4.jpg" alt="">
					<div class="b-wrapper">
									<h3 class="b-animate b-from-left    b-delay03 ">
										<span>飲食料理</span>	
									</h3>
								</div>
				</a>
			<p><a href="single.html">Cooking</a></p>
			</div>
			<div class="col-md-4 grid-top">
				<a href="<%=request.getContextPath()%>/Front-End/book/book.do?action=listBooks_ByCompositeQuery&from=Front_End&type_no=900" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="<%=request.getContextPath()%>/images/pi5.jpg" alt="">
					<div class="b-wrapper">
									<h3 class="b-animate b-from-left    b-delay03 ">
										<span>醫療保健</span>	
									</h3>
								</div>
				</a>
			<p><a href="single.html">medical treatment</a></p>
			</div>
					<div class="clearfix"> </div>
		</div>
	</div>
	<!----->
	
	<div class="content-top-bottom">
		<a href="<%=request.getContextPath()%>/Front-End/s_gro/HomePageforClub.jsp">
		<h2>讀書會</h2>
		</a>
		<div class="col-md-6 men">
			<a href="<%=request.getContextPath()%>/Front-End/s_gro/HomePageforClub.jsp?action=listS_books_ByCompositeQuery&from=Front_End&s_gro_no=110" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="<%=request.getContextPath()%>/images/ice.png" alt="">
	<!-- 				<div class="b-wrapper"> -->
	<!-- 									<h3 class="b-animate b-from-top top-in   b-delay03 "> -->
	<!-- 										<span>Lorem</span>	 -->
	<!-- 									</h3> -->
	<!-- 								</div> -->
				</a>
			
			
		</div>
		<div class="col-md-6">
			<div class="col-md1 ">
				<a href="<%=request.getContextPath()%>/Front-End/s_gro/HomePageforClub.jsp?action=listS_books_ByCompositeQuery&from=Front_End&s_gro_no=110" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="<%=request.getContextPath()%>/images/harry.jpg" alt="" style="height:390px;">
<!-- 					<div class="b-wrapper"> -->
<!-- 								<h3 class="b-animate b-from-top top-in1   b-delay03 "> -->
<!-- 									<span>Lorem</span> -->
<!-- 								</h3> -->
<!-- 							</div>  -->
							</a>
				
			</div>
<!-- 			<div class="col-md2"> -->
<!-- 				<div class="col-md-6 men1"> -->
<%-- 					<a href="single.html" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="<%=request.getContextPath()%>/images/t3.jpg" alt="" style="height:300px;"> --%>
<!-- 							<div class="b-wrapper"> -->
<!-- 									<h3 class="b-animate b-from-top top-in2   b-delay03 "> -->
<!-- 										<span>Lorem</span>	 -->
<!-- 									</h3> -->
<!-- 								</div> -->
<!-- 					</a> -->
					
<!-- 				</div> -->
<!-- 				<div class="col-md-6 men2"> -->
<%-- 					<a href="single.html" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="<%=request.getContextPath()%>/images/t4.jpg" alt=""> --%>
<!-- 							<div class="b-wrapper"> -->
<!-- 									<h3 class="b-animate b-from-top top-in2   b-delay03 "> -->
<!-- 										<span>Lorem</span>	 -->
<!-- 									</h3> -->
<!-- 								</div> -->
<!-- 					</a> -->
					
<!-- 				</div> -->
<!-- 				<div class="clearfix"> </div> -->
<!-- 			</div> -->
		</div>
		<div class="clearfix"> </div>
	</div>
	</div>
	<!---->
	
</div>


    <!-- /.content-section-b -->
<!--
    <div class="content-section-a">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Google Web Fonts and<br>Font Awesome Icons</h2>
                    <p class="lead">This template features the 'Lato' font, part of the <a target="_blank" href="http://www.google.com/fonts">Google Web Font library</a>, as well as <a target="_blank" href="http://fontawesome.io">icons from Font Awesome</a>.</p>
                </div>
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                    <img class="img-responsive" src="img/phones.png" alt="">
                </div>
            </div>

        </div>
        -->
        <!-- /.container -->

    </div>
    <!-- /.content-section-a -->

	<a  name="contact"></a>
    <div class="banner">

        <div class="container">

            <div class="row">
                <div class="col-lg-6">
                    <h2>JAVA雲端AA103 NO.4</h2>
                </div>
                <div class="col-lg-6">
                    <ul class="list-inline banner-social-buttons">
                        <li>
                            <a href="<%=request.getContextPath()%>/Front-End/s_gro/HomePageforClub.jsp" class="btn btn-default btn-lg"><i class="glyphicon glyphicon-tent"></i> <span class="network-name">讀書會</span></a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/Front-End/book/booklist.jsp" class="btn btn-default btn-lg"><i class="glyphicon glyphicon-book"></i> <span class="network-name">商城</span></a>
                        </li>

                        <li>
                            <a href="<%=request.getContextPath()%>/Front-End/Cart/cart" class="btn btn-default btn-lg"><i class="glyphicon glyphicon-shopping-cart"></i> <span class="network-name">購物車</span></a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>


