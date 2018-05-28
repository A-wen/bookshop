<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Book41 後台管理</title>
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backhome.css" />
</head>
<body>
	<!-- Header的位置-->
	<%@ include file="back-header.jsp"%>
	<!-- Header的位置-->
	<div class="container-fluid" id="main">
		<!--/col-->
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-0 col-sm-0 col-md-0 col-lg-1"></div>
			<div class="col-xs-4 col-sm-4 col-md-3 col-lg-2" id="sidebar" role="navigation">
				<!-- Sidebar的位置-->
				<%@ include file="back-sidebar.jsp"%>
				<!-- Sidebar的位置-->
			</div>

			<div class="col-xs-8 col-sm-8 col-md-9 col-lg-8 main">

				<div class="container-fluid">
					<!-- 內頁的位置-->
					<div class="row">
						<div class="col-lg-1"></div>
						<div class="col-xs-6 col-md-5">
							<div class="card card-success">
								<div class="card-block bg-success">
									<div class="rotate">
										<i class="fa fa-user fa-5x"></i>
									</div>
									<h2 class="text-uppercase">
										會員數
										</h2>
										<h1 class="display-1 pull-right">134</h1>
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-5">
							<div class="card card-danger">
								<div class="card-block bg-danger">
									<div class="rotate">
										<i class="fa fa-list fa-4x"></i>
									</div>
									<h2 class="text-uppercase">
										讀書會數
									</h2>
									<h1 class="display-1 pull-right">87</h1>
								</div>
							</div>
						</div>
						<div class="col-md-2 col-lg-1"></div>
					</div>
					<div class="row">
						<div class="col-lg-1"></div>
						<div class="col-xs-6 col-md-5">
							<div class="card card-info">
								<div class="card-block bg-info">
									<div class="rotate">
										<i class="fa fa-twitter fa-5x"></i>
									</div>
									<h2 class="text-uppercase">
										訂單數
									</h2>
									<h1 class="display-1 pull-right">125</h1>
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-5">
							<div class="card card-warning">
								<div class="card-block bg-warning">
									<div class="rotate">
										<i class="fa fa-share fa-5x"></i>
									</div>
									<h2 class="text-uppercase">
										待處理問題
										</h2>
										<h1 class="display-1 pull-right">36</h1>
								</div>
							</div>
						</div>
						<div class="col-md-2 col-lg-1"></div>
					</div>
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
		<!-- footer的位置-->
		<%@ include file="back-footer.jsp"%>
		<!-- footer的位置-->
	</footer>


</body>
</html>