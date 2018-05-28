<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Book41 後台管理</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="generator" content="Codeply">
    <script src="../../js/jquery-2.2.4.min.js"></script>
    <!-- <link rel="stylesheet" href="css/bootstrap.min.css" /> -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
    <link rel="stylesheet" href="../css/font-awesome.min.css" />
    <link rel="stylesheet" href="../css/backhome.css" />
	<script src="../../js/backbtnjquery.js"></script>

  </head>
  <body>
    <nav class="navbar navbar-dark bg-primary">
        <a id="back-home" class="navbar-brand" href="#">Book41 後台管理</a>
            <div class="pull-right">
                <button id="log-out" class="btn-info"><i class="glyphicon glyphicon-log-out"></i>登出</button>
            </div>
    </nav>
    <hr>
    
<div class="container-fluid" id="main">
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
        
        <div class="col-xs-4 col-sm-4 col-md-3 col-lg-2" id="sidebar" role="navigation">
		<!-- sidebar的位置-->
            <div class="nav nav-pills nav-stacked">
                <div class="nav-item bg-gray"><a class="nav-link">員工管理</a></div>
                <div class="list-hidden">
                    <a id="employee" class="nav-link" href="#">帳號管理</a>
                    <a id="fun" class="nav-link" href="#">權限管理</a>
                </div>
                <div class="nav-item bg-gray"><a class="nav-link">會員管理</a></div>
                    <div class="list-hidden">
                        <a id="mem" class="nav-link" href="#">會員資料管理</a>
                        <a id="order" class="nav-link" href="#">交易紀錄管理</a>
                        <a id="comm" class="nav-link" href="#">評論管理</a>
                        <a id="track" class="nav-link" href="#">追蹤紀錄管理</a>
                        <a id="coupon" class="nav-link" href="#">優惠券管理</a>
                    </div>
                <div class="nav-item bg-gray"><a class="nav-link">商品管理</a></div>
                    <div class="list-hidden">
                        <a id="book" class="nav-link" href="#">書籍管理</a>
                        <a id="booktype" class="nav-link" href="#">類別管理</a>
                        <a id="company" class="nav-link" href="#">出版社管理</a>
                    </div>
                <div class="nav-item bg-gray"><a class="nav-link">讀書會管理</a></div>
                    <div class="list-hidden">
                        <a  id="group"class="nav-link" href="#">讀書會管理</a>
                    </div>
                <div class="nav-item bg-gray"><a class="nav-link">行銷活動管理</a></div>
                    <div class="list-hidden">
                        <a id="pd" class="nav-link" href="#">優惠活動管理</a>
                        <a id="pm" class="nav-link" href="#">優惠書單管理</a>
                        <a id="edm" class="nav-link" href="#">EDM管理</a>
                        <a id="coupon" class="nav-link" href="#">優惠卷管理</a>
                    </div>
                <div class="nav-item bg-gray"><a class="nav-link">首頁內容管理</a></div>
                    <div class="list-hidden">
                        <a id="info" class="nav-link" href="#">公告管理</a>
                        <a id="cm" class="nav-link" href="#">廣告管理</a>
                        <a id="classifiedads" class="nav-link" href="#">分類推薦管理</a>
                    </div>
            </div>
		<!-- sidebar的位置-->
        </div>
        <!--/col-->

        <div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">
           
            <div id="showPage" class="container-fluid">
			<!-- 內頁的位置-->
                <%@ include file="selectEmp.jsp"%>
			<!-- 內頁的位置-->
            </div>
                <!--/row-->
        </div>
        <div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
    </div>    
        <!--/main col-->
</div>
<!--/.container-->
<footer class="container-fluid">
    <p class="pager">©III 中壢資策會 AA103 雲端Java班 第四組</p>
</footer>

    
    <script src="../js/jquery.min.js"></script>
    <script src="../js/tether.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/scripts.js"></script>
  </body>
</html>